package com.fruitnvegie.fruitnvegieapi.exceptions;

public class OrderAlreadyProcessedException extends RuntimeException {

    public OrderAlreadyProcessedException() {
        super();
    }

    public OrderAlreadyProcessedException(String message) {
        super(message);
    }

    public OrderAlreadyProcessedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderAlreadyProcessedException(Throwable cause) {
        super(cause);
    }

}
