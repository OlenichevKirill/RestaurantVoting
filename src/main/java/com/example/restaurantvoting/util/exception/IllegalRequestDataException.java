package com.example.restaurantvoting.util.exception;

import static com.example.restaurantvoting.util.exception.ErrorType.BAD_REQUEST;

public class IllegalRequestDataException extends AppException {
    public IllegalRequestDataException(String msg) {
        super(msg, BAD_REQUEST);
    }
}
