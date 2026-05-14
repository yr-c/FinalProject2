package org.yoonchan.roles;

public class UnregisteredUserException extends RuntimeException {
    public UnregisteredUserException(String message) {
        super(message);
    }

    public UnregisteredUserException() {
        super("User not found in Library's registeredUsers.");
    }
}
