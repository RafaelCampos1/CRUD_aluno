package br.com.escola.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum ErrorDescription {

    INVALID_REALID("cpf esta invalido");

    private String erroDescription;

    ErrorDescription( String erroDescription) {
        this.erroDescription = erroDescription;
    }
}
