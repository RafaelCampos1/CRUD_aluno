package br.com.escola.enums;

import lombok.Getter;

@Getter
public enum ErrorDescription {

    //TODO mudar p ingles
    INVALID_CPF("cpf esta invalido"),
    SAME_CPF("cpf igual"),
    SAME_EMAIL("email igual");

    private String erroDescription;

    ErrorDescription( String erroDescription) {
        this.erroDescription = erroDescription;
    }
}
