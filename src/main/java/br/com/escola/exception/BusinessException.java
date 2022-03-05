package br.com.escola.exception;

import br.com.escola.enums.ErrorDescription;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7806029002430564887L;

    private String message;


    public BusinessException (final ErrorDescription errorDescription) {
        this.message = errorDescription.getErroDescription();
    }



}
	    

