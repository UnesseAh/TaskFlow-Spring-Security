package com.taskflow.example.dto.taskStatus;

import com.taskflow.example.model.Task;
import com.taskflow.example.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record TaskStatusRequest(
        @NotNull(message = "Task is required")
        Integer taskId,
        @NotBlank(message = "Task status is required")
        @Pattern(regexp = "^(COMPLETED|IN_PROGRESS|OVERDUE)$", message = "Task status must be of type COMPLETED, IN PROGRESS, or OVERDUE")
        String taskStatus
) {
    public Task toTask(){
        return Task.builder()
                .id(Long.valueOf(taskId))
                .taskStatus(TaskStatus.valueOf(taskStatus))
                .build();
    }
}
