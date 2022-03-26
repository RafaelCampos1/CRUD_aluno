package com.school.exceptionhandler;

import com.school.enums.ErrorDescription;

public class ConflictException extends RuntimeException {

    public ConflictException(final ErrorDescription errorDescription) {
        super(errorDescription.getDescription());
    }
}
