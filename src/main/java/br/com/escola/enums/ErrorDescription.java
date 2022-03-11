package br.com.escola.enums;

import lombok.Getter;

@Getter
public enum ErrorDescription {

    //TODO mudar p ingles
    INVALID_REALID("cpf esta invalido"),
    SAME_REALID("cpf igual"),
    SAME_EMAIL("email igual");

    private String erroDescription;

    ErrorDescription( String erroDescription) {
        this.erroDescription = erroDescription;
    }
}
