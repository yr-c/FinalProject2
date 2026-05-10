package org.yoonchan;

public class Magazine extends Item {
    private static int nextId = 1;

    // For CSV functionality
    public Magazine(String id, String title, Item.Status status) {
        super(id, title, status);
    }

    public Magazine(String title, Item.Status status) {
        super(String.format("M%04d", nextId++), title, status);
    }
}
