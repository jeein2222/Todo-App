package com.codestates.todoApp.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_FOUND(404, "Member not found."),
    TODO_NOT_FOUND(404, "Todo not found."),

    MEMBER_EXISTS(409, "Member already exists"),
    TODO_EXSITS(409,"Todo already exists.");



    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
