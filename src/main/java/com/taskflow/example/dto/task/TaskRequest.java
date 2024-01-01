package com.taskflow.example.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskflow.example.model.Task;
import com.taskflow.example.model.enums.TaskStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record TaskRequest(
        @NotBlank(message = "Task title is required")
        @Size(min = 2, max = 70, message = "Task title must be between 2 and 70 characters")
        String title,
        @NotBlank(message = "Task description is required")
        @Size(min = 2, max = 255, message = "Task description must be between 2 and 255 characters")
        String description,
        @NotNull(message = "Start date is required")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,
        @NotNull(message = "End date is required")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,
        @NotBlank(message = "Task status is required")
        @Pattern(regexp = "^(COMPLETED|IN_PROGRESS|OVERDUE)$", message = "Task status must be of type COMPLETED, IN PROGRESS, or OVERDUE")
        String taskStatus
) {
    public Task toTask(){
        return Task.builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .taskStatus(TaskStatus.valueOf(taskStatus))
                .build();
    }
}
