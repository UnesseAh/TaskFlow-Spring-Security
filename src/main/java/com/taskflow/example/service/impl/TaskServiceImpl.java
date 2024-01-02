package com.taskflow.example.service.impl;

import com.taskflow.example.handler.exception.ResourceNotFoundException;
import com.taskflow.example.model.Tag;
import com.taskflow.example.model.Task;
import com.taskflow.example.model.enums.TaskStatus;
import com.taskflow.example.repository.TagRepository;
import com.taskflow.example.repository.TaskRepository;
import com.taskflow.example.service.TaskService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TagRepository tagRepository) {
        this.taskRepository = taskRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public Task save(Task task, List<Integer> tagList) {

        if (task.getStartDate().isAfter(LocalDate.now().plusDays(3))){
            throw new IllegalArgumentException("Restrict task start date to a maximum of 3 days in advance.");
        }
        if(task.getEndDate().isAfter(LocalDate.now().plusDays(3))){
            throw new IllegalArgumentException("Restrict task end date to a maximum of 3 days in advance.");
        }

        tagList.stream().forEach(tag -> {
            if (!tagRepository.findById(Long.valueOf(tag)).isPresent()) {
                throw new IllegalArgumentException("Tag with id " + tag + " doesn't exist");
            }
        });

        if(tagList.size() <= 1){
            throw new IllegalArgumentException("You must enter more than one tag");
        }

        List<Tag> tagsToSave = new ArrayList<>();
        tagList.forEach(tag -> tagsToSave.add(tagRepository.findById(Long.valueOf(tag)).get()));

        task.setTags(tagsToSave);


        return taskRepository.save(task);
    }

    @Override
    public Task changeStatus(Task task){
        Optional<Task> foundTask = taskRepository.findById(task.getId());
        if (foundTask.isEmpty()){
            throw new ResourceNotFoundException("Task with id " + task.getId() + " is not found ");
        }

        if (task.getTaskStatus().equals(TaskStatus.COMPLETED) && foundTask.get().getEndDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("You can't mark this task as completed because its end date has already passed");
        }
        foundTask.get().setTaskStatus(TaskStatus.COMPLETED);
        return taskRepository.save(foundTask.get());
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void markTaskAsCompleted(){
        LocalDate currentDate = LocalDate.now();
        List<Task> overdueTasks = taskRepository.findByEndDateBeforeAndTaskStatusNot(currentDate, TaskStatus.COMPLETED);

        for (Task task : overdueTasks) {
            task.setTaskStatus(TaskStatus.OVERDUE);
        }
        taskRepository.saveAll(overdueTasks);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public void delete(Long id) {

    }
}
