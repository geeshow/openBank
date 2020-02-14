package com.ken207.openbank.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter @Setter
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class BizRuntimeException extends RuntimeException {

    private String message;
    private HttpStatus statusCode;

    public BizRuntimeException(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public BizRuntimeException(String message) {
        super(message);
        this.message = message;
    }

    public BizRuntimeException(Throwable cause) {
        super(cause);
    }

    protected BizRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        MessageSource
    }
}
