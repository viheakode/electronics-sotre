package com.viheakode.electronic_store.exception;

import com.viheakode.electronic_store.util.ApiResponseStructure;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, HttpServletRequest request){
        return ApiResponseStructure.response(false, 404, ex.getMessage(), null, request.getRequestURI(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, HttpServletRequest request){
        return ApiResponseStructure.response(false, 400, ex.getMessage(), null, request.getRequestURI(), HttpStatus.BAD_REQUEST);
    }
}
