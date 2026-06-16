package com.sp.reservationservice.infrastructure.exception;

import com.sp.reservationservice.domain.exception.ConflictException;
import com.sp.reservationservice.domain.exception.DomainException;
import com.sp.reservationservice.domain.exception.ForbiddenException;
import com.sp.reservationservice.domain.exception.NotFoundException;
import com.sp.reservationservice.infrastructure.constants.InfrastructureConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(DomainException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(ex.getMessage(), InfrastructureConstants.BAD_REQUEST));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException ex) {
        return ResponseEntity.status(InfrastructureConstants.FORBIDDEN)
                .body(new ErrorResponse(ex.getMessage(), InfrastructureConstants.FORBIDDEN));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
        return ResponseEntity.status(InfrastructureConstants.CONFLICT)
                .body(new ErrorResponse(ex.getMessage(), InfrastructureConstants.CONFLICT));
    }
    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<ErrorResponse> handleInfrastructureException(InfrastructureException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new ErrorResponse(ex.getMessage(), ex.getHttpStatus()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(InfrastructureConstants.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage(), InfrastructureConstants.NOT_FOUND));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(),
                        error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ErrorResponse(InfrastructureConstants.MSG_INVALID_DATA, InfrastructureConstants.BAD_REQUEST, errors));

    }
}