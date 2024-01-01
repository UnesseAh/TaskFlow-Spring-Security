package com.taskflow.example.service.impl;

import com.taskflow.example.model.Task;
import com.taskflow.example.repository.TaskRepository;
import com.taskflow.example.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        if (task.getStartDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Start date must be in the future");
        }
        if(task.getEndDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("End date must be in the future");
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
