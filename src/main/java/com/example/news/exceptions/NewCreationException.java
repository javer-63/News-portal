package com.example.news.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NewCreationException extends RuntimeException {
    public NewCreationException(String message) {
        super("Новость не создана. " + message);
    }
}
