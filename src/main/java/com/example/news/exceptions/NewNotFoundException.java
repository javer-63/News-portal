package com.example.news.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewNotFoundException extends RuntimeException {
    public NewNotFoundException(Long id) {
        super("Новость с ID " + id + " не найдена");
    }
}
