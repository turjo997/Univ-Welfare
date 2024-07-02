package com.suffixit.kieb.ExceptionHandler;

import com.suffixit.kieb.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;


import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({org.springframework.web.bind.MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleValidationException(org.springframework.web.bind.MethodArgumentNotValidException ex) {

        String validationErrors;
        validationErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(validationErrors);
    }



    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatusCode status = ex.getStatusCode();
        String errorMessage = ex.getReason();
        String formattedErrorMessage = "Error: " + status + " - " + errorMessage;
        return ResponseEntity.status(status).body(formattedErrorMessage);
    }



    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleAllException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }




}

