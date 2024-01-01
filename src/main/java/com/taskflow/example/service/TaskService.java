package com.taskflow.example.service;

import com.taskflow.example.model.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task, List<Integer> tagList);
    List<Task> getAll();
    void delete(Long id);
}
