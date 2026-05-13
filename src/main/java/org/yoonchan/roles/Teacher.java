package org.yoonchan.roles;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import org.yoonchan.entities.Item;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Teacher extends User {
    @Setter private static int nextId = 1;

    // For CSV functionality
    public Teacher(String id, String name, List<Item> borrowedItems) {
        super(id, name, borrowedItems);
    }

    public Teacher(String name) {
        super(String.format("T%05d", nextId++), name);
    }
}
