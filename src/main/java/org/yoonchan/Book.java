package org.yoonchan;

public class Book extends Item {
    private static int nextId = 1;

    // For CSV functionality
    public Book(String id, String title, Status status) {
        super(id, title, status);
    }

    public Book(String title, Status status) {
        super(String.format("B%04d", nextId++), title, status);
    }
}
