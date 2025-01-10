package com.example.restaurantvoting.util.exception;

import static com.example.restaurantvoting.util.exception.ErrorType.BAD_REQUEST;

public class VoteException extends AppException {

    public VoteException(String message) {
        super(message, BAD_REQUEST);
    }
}
