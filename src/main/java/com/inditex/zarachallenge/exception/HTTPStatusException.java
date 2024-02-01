package com.inditex.zarachallenge.exception;

import lombok.Getter;
import org.mockserver.model.HttpStatusCode;

@Getter
public class HTTPStatusException extends Exception{
    private final HttpStatusCode statusCode;

    public HTTPStatusException(String message, HttpStatusCode httpStatusCode) {
        super(message);
        this.statusCode = httpStatusCode;
    }
}
