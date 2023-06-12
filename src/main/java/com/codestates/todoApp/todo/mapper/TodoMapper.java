package com.codestates.todoApp.todo.mapper;


import com.codestates.todoApp.todo.dto.TodoDto;
import com.codestates.todoApp.todo.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
    Todo todoPostToTodo(TodoDto.Post requestBody);

    Todo todoPatchToTodo(TodoDto.Patch requestBody);

    TodoDto.Response todoTotodoResponse(Todo todo);
    List<TodoDto.Response> todosTotodoResponses(List<Todo> todos);

}
