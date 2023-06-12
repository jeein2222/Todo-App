package com.codestates.todoApp.todo.dto;

import com.codestates.todoApp.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class TodoDto {

    @Getter
    @AllArgsConstructor
    public static class Post{
        @NotBlank
        private String title;

        private int todoOrder;

        private boolean completed;

    }

    @AllArgsConstructor
    @Getter
    public static class Patch{
        private long todoId;

        @NotBlank
        private String title;

        private int todoOrder;

        private boolean completed;

        public void setTodoId(long todoId){
            this.todoId = todoId;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class Response{
        private long todoId;
        private long memberId;
        private String title;
        private int todoOrder;
        private boolean completed;

        public void setMember(Member member){
            this.memberId = member.getMemberId();
        }

    }
}
