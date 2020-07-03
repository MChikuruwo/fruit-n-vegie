package com.fruitnvegie.fruitnvegieapi.exceptions;

public final class InvalidOldPasswordException extends RuntimeException{

    public InvalidOldPasswordException() {
        super();
    }

    public InvalidOldPasswordException(String message) {
        super(message);
    }

    public InvalidOldPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOldPasswordException(Throwable cause) {
        super(cause);
    }
}
