package com.taskflow.example.repository;

import com.taskflow.example.model.Task;
import com.taskflow.example.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEndDateBeforeAndTaskStatusNot(LocalDate endDate, TaskStatus taskStatus);
}
