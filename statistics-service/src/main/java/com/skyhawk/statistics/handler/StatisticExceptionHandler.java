package com.skyhawk.statistics.handler;

import com.skyhawk.statistics.exception.ClientErrorException;
import com.skyhawk.statistics.exception.NotPlayerStatisticsAvailable;
import com.skyhawk.statistics.exception.NotTeamStatisticsAvailable;
import com.skyhawk.statistics.exception.ServerErrorException;
import com.skyhawk.statistics.handler.error.ErrorDetails;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class StatisticExceptionHandler {

    @ExceptionHandler(ClientErrorException.class)
    public ResponseEntity<ErrorDetails> handleClientError(ClientErrorException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<ErrorDetails> handleServerError(ServerErrorException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(NotPlayerStatisticsAvailable.class)
    public ResponseEntity<ErrorDetails> handleNotPlayerStatisticsAvailable(NotPlayerStatisticsAvailable ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NotTeamStatisticsAvailable.class)
    public ResponseEntity<ErrorDetails> handleNotTeamStatisticsAvailable(NotTeamStatisticsAvailable ex, WebRequest request) {
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
