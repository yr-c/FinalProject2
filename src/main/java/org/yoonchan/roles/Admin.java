package org.yoonchan.roles;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import org.yoonchan.entities.Item;
import org.yoonchan.interfaces.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Admin extends User implements Reporter, ItemRegistrar, UserRegistrar, CSVPersister {
    @Setter private static int nextId = 1;

    // For CSV functionality
    public Admin(String id, String name, List<Item> borrowedItems) {
        super(id, name, borrowedItems);
    }

    public Admin(String name) {
        super(String.format("A%05d", nextId++), name);
    }

    @Override
    public int getMaximumBorrowAmount() {
        return Integer.MAX_VALUE; // Unlimited for Admins
    }
}
