package br.com.escola.exceptionhandler;


import br.com.escola.enums.ErrorDescription;
import lombok.Data;

import java.util.Date;

@Data
public class MessageExceptionHandler {
    private Date timestamp;
    private Integer status;
    private String message;

    public MessageExceptionHandler(Date timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }
}
