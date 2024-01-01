package com.taskflow.example.dto.task;

import com.taskflow.example.model.Task;
import com.taskflow.example.model.enums.TaskStatus;

import java.time.LocalDate;

public record TaskResponse(
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        TaskStatus taskStatus
) {
    public static TaskResponse taskResponseDto(Task task){
        return new TaskResponse(
                task.getTitle(),
                task.getDescription(),
                task.getStartDate(),
                task.getEndDate(),
                task.getTaskStatus()
                );
    }
}
