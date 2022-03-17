package br.com.escola.exceptionhandler;

import br.com.escola.enums.ErrorDescription;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final ErrorDescription errorDescription){
        super(errorDescription.getErrorDescription());
    }


}
