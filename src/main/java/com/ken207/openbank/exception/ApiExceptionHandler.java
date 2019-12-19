package com.ken207.openbank.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final Map<Class<? extends Exception>, String> messageMappings =
            Collections.unmodifiableMap(new LinkedHashMap() {
                {
                    put(HttpMessageNotReadableException.class, "Request body is invalid");
                }
            });


    @ExceptionHandler(BizRuntimeException.class)
    public ResponseEntity<Object> handleCustomException(BizRuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, null, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private String resolveMessage(Exception ex, final String defaultMessage) {
        return messageMappings.entrySet().stream()
                .filter(entry -> entry.getKey().isAssignableFrom(ex.getClass()))
                .findFirst().map(Map.Entry::getValue).orElse(defaultMessage);
    }

    private ApiError createApiError(Exception ex, HttpStatus status) {
        ApiError apiError = new ApiError();
        apiError.setMessage(resolveMessage(ex, ex.getMessage()));
        apiError.setStatus(status);
        return apiError;
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ApiError apiError = createApiError(ex, status);
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex, status);
        ex.getBindingResult().getGlobalErrors()
                .forEach(e -> apiError.addDetail(e.getObjectName(), getMessage(e, request)));
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> apiError.addDetail(e.getField(), getMessage(e, request)));

        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private String getMessage(ObjectError e, WebRequest request) {
        return request.getParameter(e.getObjectName());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex, status);
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    final String target = error.getField();
                    final String message = error.getDefaultMessage();
                    apiError.addDetail(target, message);
                });

        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex, status);
        String target = (String) ex.getValue();
        String message = "is valid";
        apiError.addDetail(target, message);

        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }


}
