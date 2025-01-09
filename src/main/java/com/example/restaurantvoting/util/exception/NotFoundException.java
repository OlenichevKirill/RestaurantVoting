package com.example.restaurantvoting.util.exception;

import static com.example.restaurantvoting.util.exception.ErrorType.NOT_FOUND;

public class NotFoundException extends AppException {

    public NotFoundException(String msg) {
        super(msg, NOT_FOUND);
    }
}
