package com.example.news.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NewValidationException extends RuntimeException {
    public NewValidationException(String message) {
        super("Ошибка! " + message);
    }
}
