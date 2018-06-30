package com.search.service;

import exceptions.StorageException;
import exceptions.StorageFileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorControllerAdvice {

    @ExceptionHandler(StorageFileNotFoundException.class)    // 404
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        log.error(exc.getMessage());
        return  new ResponseEntity(exc.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StorageException.class)    // 500
    public ResponseEntity<?> StorageException(StorageException exc) {
        log.error(exc.getMessage());
        return new ResponseEntity(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
