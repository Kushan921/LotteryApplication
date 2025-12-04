package com.lottery.lottery.exception;

import com.lottery.lottery.constant.ApiConstants;
import com.lottery.lottery.dto.responses.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResponse(
                        ApiConstants.STATUS_ERROR,
                        ex.getMessage(),
                        null
                ));
    }

    // handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResponse(
                        ApiConstants.STATUS_ERROR,
                        ex.getMessage(),
                        null
                ));
    }

    // catch all for other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse(
                        ApiConstants.STATUS_ERROR,
                        "An unexpected error occurred: " + ex.getMessage(),
                        null
                ));
    }
}
