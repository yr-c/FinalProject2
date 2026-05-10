package org.yoonchan;

import java.util.List;

public class Admin extends User{
    private static int nextId = 1;

    // For CSV functionality
    public Admin(String id, String name, List<Item> borrowedItems) {
        super(id, name, borrowedItems);
    }

    public Admin(String name) {
        super(String.format("A%05d", nextId++), name);
    }
}
