package com.codestates.todoApp.todo.repository;

import com.codestates.todoApp.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByTitle(String title);
}
