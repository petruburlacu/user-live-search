package com.search.demo.exception;

import com.search.demo.constants.RequestStatus;
import com.search.demo.model.ResponseAPI;
import com.search.demo.util.RequestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ResponseAPI> handleNullPointerException(NullPointerException ex) {
        return RequestHelper.responseEntityHelper("Null pointer exception encountered. Please contact an administrator.", ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new ResponseAPI(RequestStatus.ERROR, "Request validation failed.", errors), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InputValidationException.class)
    protected ResponseEntity<ResponseAPI> handleInputValidationException(InputValidationException ex) {
        return RequestHelper.responseEntityHelper("Input failed validation parameters. Please try again.", ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ResponseAPI> handleIllegalArgumentException(IllegalArgumentException ex) {
        return RequestHelper.responseEntityHelper("Illegal argument exception. Please try again.", ex);
    }
}
