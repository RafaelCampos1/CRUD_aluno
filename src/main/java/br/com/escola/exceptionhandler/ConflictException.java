package br.com.escola.exceptionhandler;

import br.com.escola.enums.ErrorDescription;

public class ConflictException extends RuntimeException{

    public ConflictException(final ErrorDescription errorDescription) {
        super(errorDescription.getErrorDescription());
    }
}
