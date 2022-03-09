package br.com.escola.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

public enum ErrorDescription {

    INVALID_REALID("cpf esta invalido"),
    SAME_REALID("cpf igual"),
    SAME_EMAIL("email igual");

    private String erroDescription;

    ErrorDescription( String erroDescription) {
        this.erroDescription = erroDescription;
    }
}
