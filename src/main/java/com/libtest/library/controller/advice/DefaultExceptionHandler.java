package com.libtest.library.controller.advice;

import com.libtest.library.exception.EntityNotFoundException;
import com.libtest.library.exception.EntityPersistenceException;
import com.libtest.library.exception.IllegalNumberException;
import com.libtest.library.exception.PageNumberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> entityFoundHandle(Exception e) {
        log.error(e.getMessage());

        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EntityPersistenceException.class)
    public ResponseEntity<Object> persistenceHandle(Exception e) {
        log.error(e.getMessage());

        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalNumberException.class, PageNumberException.class})
    public ResponseEntity<Object> illegalNumberAndPageHandle(Exception e) {

        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
