package com.nikaru.fixit.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikaru.fixit.domain.entities.Task;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
