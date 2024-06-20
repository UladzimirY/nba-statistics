package com.skyhawk.player.handler;

import com.skyhawk.player.exception.PlayerNotFoundException;
import com.skyhawk.player.handler.error.ErrorDetails;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class PlayerExceptionHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlePlayerNotFoundException(PlayerNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDetails> handleValidationException(ValidationException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ErrorDetails> buildErrorResponse(Exception ex, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatusCode(status.value());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, status);
    }
}
