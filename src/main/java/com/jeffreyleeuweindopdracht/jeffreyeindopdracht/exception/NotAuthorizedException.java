package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception;

public class NotAuthorizedException extends RuntimeException{

    public NotAuthorizedException() {
        super();
    }

    public NotAuthorizedException(String message) {
        super(message);
    }
}
