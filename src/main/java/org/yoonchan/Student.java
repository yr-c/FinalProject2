package org.yoonchan;

import java.util.List;

public class Student extends User {
    private static int nextId = 1;

    // For CSV functionality
    public Student(String id, String name, List<Item> borrowedItems) {
        super(id, name, borrowedItems);
    }

    public Student(String name) {
        super(String.format("S%05d", nextId++), name);
    }
}
