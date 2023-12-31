package com.taskflow.example.controller;

import com.taskflow.example.dto.task.TaskRequest;
import com.taskflow.example.dto.taskStatus.TaskStatusRequest;
import com.taskflow.example.model.Task;
import com.taskflow.example.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated TaskRequest taskRequest){
        Task task = taskRequest.toTask();
        List<Integer> tagList = taskRequest.tags();
        return ResponseEntity.ok(taskService.save(task, tagList));
    }

    @PostMapping("/change-task-status")
    public ResponseEntity<?> changeStatus(@RequestBody @Validated TaskStatusRequest taskStatusRequest){
        Task task = taskStatusRequest.toTask();
        return ResponseEntity.ok(taskService.changeStatus(task));


    }
}
