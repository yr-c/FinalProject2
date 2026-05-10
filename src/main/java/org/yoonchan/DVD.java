package org.yoonchan;

public class DVD extends Item {
    private static int nextId = 1;

    // For CSV functionality
    public DVD(String id, String title, Status status) {
        super(id, title, status);
    }

    public DVD(String title, Status status) {
        super(String.format("D%04d", nextId++), title, status);
    }
}
