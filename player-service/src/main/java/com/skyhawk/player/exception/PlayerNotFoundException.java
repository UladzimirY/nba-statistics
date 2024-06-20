package com.skyhawk.player.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException() {
    }

    public PlayerNotFoundException(String message) {
        super(message);
    }

    public PlayerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
