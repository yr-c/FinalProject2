package org.yoonchan.roles;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import org.yoonchan.CSVPersister;
import org.yoonchan.entities.Item;
import org.yoonchan.Reporter;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Admin extends User implements Reporter, CSVPersister {
    @Setter private static int nextId = 1;

    // For CSV functionality
    public Admin(String id, String name, List<Item> borrowedItems) {
        super(id, name, borrowedItems);
    }

    public Admin(String name) {
        super(String.format("A%05d", nextId++), name);
    }

    /**
     * Generates a report of all borrowed/in-store/lost Items from the Library's itemCatalogue.
     * Prints out a formatted list of each item, its type, and its status.
     */
    @Override
    public void generateReport() {
        System.out.println();
    }
}
