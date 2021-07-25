package com.libtest.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PageNumberException extends RuntimeException{

    public PageNumberException() {
    }

    public PageNumberException(String message) {
        super(message);
    }
}
