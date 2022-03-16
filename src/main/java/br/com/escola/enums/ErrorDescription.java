package br.com.escola.enums;

import lombok.Getter;

@Getter
public enum ErrorDescription {

    INVALID_CPF("INVALID CPF"),
    SAME_CPF("CPF ALREADY REGISTERED"),
    SAME_EMAIL("EMAIL ALREADY REGISTERED"),
    STUDENT_NOT_FOUND("STUDENT NOT FOUND"),
    STUDENTS_NOT_FOUND("THERE IS NO STUDENT YET");

    private final String errorDescription;

    ErrorDescription( String errorDescription) {
        this.errorDescription = errorDescription;
    }


}
