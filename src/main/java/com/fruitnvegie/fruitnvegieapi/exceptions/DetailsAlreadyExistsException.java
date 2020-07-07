package com.fruitnvegie.fruitnvegieapi.exceptions;

public class DetailsAlreadyExistsException extends RuntimeException{

    public DetailsAlreadyExistsException() {
        super();
    }

    public DetailsAlreadyExistsException(String message) {
        super(message);
    }

    public DetailsAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DetailsAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
