package com.school.exceptionhandler;

import lombok.Getter;

import java.util.Date;

@Getter
public class MessageExceptionHandler {
    private final Date timestamp;
    private final Integer status;
    private final String message;

    public MessageExceptionHandler(Date timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }
}
