package com.codestates.todoApp.todo.controller;

import com.codestates.todoApp.dto.MultiResponseDto;
import com.codestates.todoApp.dto.SingleResponseDto;
import com.codestates.todoApp.member.service.MemberService;
import com.codestates.todoApp.todo.dto.TodoDto;
import com.codestates.todoApp.todo.entity.Todo;
import com.codestates.todoApp.todo.mapper.TodoMapper;
import com.codestates.todoApp.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/todos")
@Validated
@Slf4j
public class TodoController {

    private final static String TODO_DEFAULT_URL = "/todos";
    private final TodoService todoService;
    private final MemberService memberService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, MemberService memberService, TodoMapper mapper) {
        this.todoService = todoService;
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post requestBody){
        Todo todo = mapper.todoPostToTodo(requestBody);

        //사용자 정보 추출
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //사용자 이메일 추출
        String userName = (String) authentication.getPrincipal();

        System.out.println("userName: "+userName);

        //사용자 설정
        todo.setMember(memberService.findVerifiedMember(userName));

        Todo createdTodo = todoService.createTodo(todo);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.todoTotodoResponse(createdTodo)),
                HttpStatus.CREATED
                );
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(
            @PathVariable("todo-id") @Positive long todoId,
            @Valid @RequestBody TodoDto.Patch requestBody)
    {
        Todo todo = mapper.todoPatchToTodo(requestBody);
        todo.setTodoId(todoId);
        Todo updatedTodo =todoService.updateTodo(todo);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.todoTotodoResponse(updatedTodo)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive long todoId){
        Todo findTodo = todoService.findTodo(todoId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.todoTotodoResponse(findTodo)),
                HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity getTodos(@Positive @RequestParam int page, @Positive @RequestParam int size){
        Page<Todo> pageTodos = todoService.findTodos(page-1, size);
        List<Todo> todos = pageTodos.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.todosTotodoResponses(todos),pageTodos),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(
            @PathVariable("todo-id") @Positive long todoId
    ){
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTodos(){
        todoService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
