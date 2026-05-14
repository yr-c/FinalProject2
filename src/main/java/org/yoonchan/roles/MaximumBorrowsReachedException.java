package org.yoonchan.roles;

public class MaximumBorrowsReachedException extends RuntimeException {
    public MaximumBorrowsReachedException(String message) {
        super(message);
    }
}
