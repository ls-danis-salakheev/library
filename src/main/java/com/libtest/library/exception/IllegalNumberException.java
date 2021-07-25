package com.libtest.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalNumberException extends RuntimeException {

    public IllegalNumberException(String message) {
        super(message);
    }

    public IllegalNumberException() {
    }
}
