package br.com.escola.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//@ControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }


}
