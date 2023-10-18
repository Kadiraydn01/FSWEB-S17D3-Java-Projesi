package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

@ExceptionHandler
public ResponseEntity<ZooErrorResponse> handleException(ZooException exception){
    ZooErrorResponse response = new ZooErrorResponse(
            exception.getStatus().value(), exception.getMessage(), System.currentTimeMillis()
    );
    return new ResponseEntity<>(response,exception.getStatus());
  }
@ExceptionHandler
public ResponseEntity<ZooErrorResponse>handleException(Exception exception){
    ZooErrorResponse response = new ZooErrorResponse(
            HttpStatus.BAD_REQUEST.value(), exception.getMessage(),System.currentTimeMillis()
    );
    log.error(exception.getMessage());
    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
  }
}
