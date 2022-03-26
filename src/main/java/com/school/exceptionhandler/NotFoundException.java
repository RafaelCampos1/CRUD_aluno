package com.school.exceptionhandler;

import com.school.enums.ErrorDescription;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final ErrorDescription errorDescription){
        super(errorDescription.getDescription());
    }


}
