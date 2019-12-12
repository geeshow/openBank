package com.ken207.openbank.exception;

public class BizRuntimeException extends RuntimeException {
    public BizRuntimeException() {
        super();
    }

    public BizRuntimeException(String message) {
        super(message);
    }

    public BizRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizRuntimeException(Throwable cause) {
        super(cause);
    }

    protected BizRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
