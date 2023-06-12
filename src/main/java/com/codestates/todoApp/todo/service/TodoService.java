package com.codestates.todoApp.todo.service;

import com.codestates.todoApp.exception.BusinessLogicException;
import com.codestates.todoApp.exception.ExceptionCode;
import com.codestates.todoApp.todo.entity.Todo;
import com.codestates.todoApp.todo.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        Todo savedTodo = todoRepository.save(todo);
        return savedTodo;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Todo updateTodo(Todo todo) {
        Todo findTodo = findVerifiedTodo(todo.getTodoId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));

        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(todoOrder -> findTodo.setTodoOrder(todoOrder));

        Optional.ofNullable(todo.isCompleted())
                .ifPresent(isCompleted -> findTodo.setCompleted(isCompleted));

        return todoRepository.save(findTodo);
    }


    public Todo findTodo(long todoId) {
        return findVerifiedTodo(todoId);
    }

    public Page<Todo> findTodos(int page, int size) {
        return todoRepository.findAll(PageRequest.of(page, size, Sort.by("todoId").descending()));
    }

    public void deleteTodo(long todoId) {
        Todo findTodo = findVerifiedTodo(todoId);
        todoRepository.delete(findTodo);
    }

    public void deleteAll(){
        todoRepository.deleteAll();
    }


    public Todo findVerifiedTodo(long todoId){
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo findTodo = optionalTodo.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
        return findTodo;
    }

}
