package com.taskflow.example.service.impl;

import com.taskflow.example.model.Task;
import com.taskflow.example.repository.TagRepository;
import com.taskflow.example.repository.TaskRepository;
import com.taskflow.example.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        if (task.getStartDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Start date must be in the future.");
        }
        if(task.getEndDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("End date must be in the future.");
        }
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

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public void delete(Long id) {

    }
}
