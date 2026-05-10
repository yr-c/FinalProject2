package org.yoonchan;

import java.util.List;

public class Teacher extends User {
    private static int nextId = 1;

    // For CSV functionality
    public Teacher(String id, String name, List<Item> borrowedItems) {
        super(id, name, borrowedItems);
    }

    public Teacher(String name) {
        super(String.format("T%05d", nextId++), name);
    }
}
