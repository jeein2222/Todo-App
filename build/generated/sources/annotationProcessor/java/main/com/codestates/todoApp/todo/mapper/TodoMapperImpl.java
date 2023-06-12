package com.codestates.todoApp.todo.mapper;

import com.codestates.todoApp.todo.dto.TodoDto;
import com.codestates.todoApp.todo.entity.Todo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-12T14:53:04+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.19 (Oracle Corporation)"
)
@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public Todo todoPostToTodo(TodoDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setTitle( requestBody.getTitle() );
        todo.setTodoOrder( requestBody.getTodoOrder() );
        todo.setCompleted( requestBody.isCompleted() );

        return todo;
    }

    @Override
    public Todo todoPatchToTodo(TodoDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setTodoId( requestBody.getTodoId() );
        todo.setTitle( requestBody.getTitle() );
        todo.setTodoOrder( requestBody.getTodoOrder() );
        todo.setCompleted( requestBody.isCompleted() );

        return todo;
    }

    @Override
    public TodoDto.Response todoTotodoResponse(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        long todoId = 0L;
        String title = null;
        int todoOrder = 0;
        boolean completed = false;

        todoId = todo.getTodoId();
        title = todo.getTitle();
        todoOrder = todo.getTodoOrder();
        completed = todo.isCompleted();

        long memberId = 0L;

        TodoDto.Response response = new TodoDto.Response( todoId, memberId, title, todoOrder, completed );

        response.setMember( todo.getMember() );

        return response;
    }

    @Override
    public List<TodoDto.Response> todosTotodoResponses(List<Todo> todos) {
        if ( todos == null ) {
            return null;
        }

        List<TodoDto.Response> list = new ArrayList<TodoDto.Response>( todos.size() );
        for ( Todo todo : todos ) {
            list.add( todoTotodoResponse( todo ) );
        }

        return list;
    }
}
