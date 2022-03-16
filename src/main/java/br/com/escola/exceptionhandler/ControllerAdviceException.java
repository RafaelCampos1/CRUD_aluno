package br.com.escola.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ControllerAdviceException {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageExceptionHandler> productNotFound(Exception exception){
        MessageExceptionHandler costumeError = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(),exception.getMessage());
        return new ResponseEntity<>(costumeError,HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> argumentsNotValid(MethodArgumentNotValidException notValidException) {
        BindingResult bindingResult = notValidException.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();

        StringBuilder stringBuilder = new StringBuilder("os campos seguintes nao podem ser nulos: ");

        fieldErrorList.forEach(FieldError -> stringBuilder.append(FieldError.getField()).append(" "));

        MessageExceptionHandler messageExceptionHandler = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), stringBuilder.toString());

        return new ResponseEntity<>(messageExceptionHandler, HttpStatus.BAD_REQUEST);
    }
}

