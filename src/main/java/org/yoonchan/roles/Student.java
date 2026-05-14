package org.yoonchan.roles;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import org.yoonchan.entities.Item;
import org.yoonchan.util.Constants;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Student extends User {
    @Setter private static int nextId = 1;

    // For CSV functionality
    public Student(String id, String name, List<Item> borrowedItems) {
        super(id, name, borrowedItems);
    }

    public Student(String name) {
        super(String.format("S%05d", nextId++), name);
    }

    @Override
    public int getMaximumBorrowAmount() {
        return Constants.MAX_ITEMS_STUDENT;
    }
}
