package br.com.escola.exception;

import br.com.escola.enums.ErrorDescription;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -7806029002430564887L;

    private String message;

    public BusinessException(final ErrorDescription errorDescription) {
        this.message = errorDescription.getErrorDescription();
    }

}
	    

