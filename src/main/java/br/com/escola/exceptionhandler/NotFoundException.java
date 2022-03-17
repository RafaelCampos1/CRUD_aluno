package br.com.escola.exceptionhandler;

import br.com.escola.enums.ErrorDescription;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final ErrorDescription errorDescription){
        super(errorDescription.getErrorDescription());
    }


}
