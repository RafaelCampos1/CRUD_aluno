package com.school.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ControllerAdviceException {


    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<MessageExceptionHandler> resourceAlreadyExists(ConflictException exception) {
        MessageExceptionHandler costumeError = new MessageExceptionHandler(
                new Date(), HttpStatus.CONFLICT.value(), exception.getMessage());
        return new ResponseEntity<>(costumeError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> entityNotFound(NotFoundException exception) {
        MessageExceptionHandler costumeError = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(costumeError, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> argumentsNotValid(MethodArgumentNotValidException notValidException) {
        BindingResult bindingResult = notValidException.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();

        StringBuilder stringBuilder = new StringBuilder("the following fields cannot be null: ");

        fieldErrorList.forEach(FieldError -> stringBuilder.append(FieldError.getField()).append(" "));

        MessageExceptionHandler messageExceptionHandler = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), stringBuilder.toString());

        return new ResponseEntity<>(messageExceptionHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<MessageExceptionHandler> jsonParseError() {
        MessageExceptionHandler costumeError = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "Invalid request, probably the parameters are invalid");
        return new ResponseEntity<>(costumeError, HttpStatus.BAD_REQUEST);
    }


}

