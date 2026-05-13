package org.yoonchan.roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.yoonchan.entities.Item;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public abstract class User {
    protected String id;
    @Setter protected String name;
    @Setter protected List<Item> borrowedItems;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    /**
     * Adds an item to the User's borrowedItems list. Removes that same item from the Library's
     * itemCatalogue.
     * @param item The item to be borrowed.
     */
    public void borrowItem(Item item) {

    }

    /**
     * Returns an item to the Library from the User's borrowedItems list. Adds that same item from the Library's
     * itemCatalogue.
     * @param item The item to be returned.
     */
    public void returnItem(Item item) {

    }

    /**
     * Recursively searches for an Item in the Library's itemCatalogue using a keyword, looking for a
     * title or author (case-insensitive).
     * <p>
     * If an item has more than one copy, the search result will contain only one copy of the item.
     * @return Whether the search found a match for the item.
     */
    public boolean searchItemRecursive(String keyword) {
        return false;
    }

    /**
     * Searches using a Stream for an Item in the Library's itemCatalogue using a keyword, looking for a
     * title or author (case-insensitive).
     * <p>
     * If an item has more than one copy, the search result will contain only one copy of the item.
     * @return Whether the search found a match for the item.
     */
    public boolean searchItemStream(String keyword) {
        return false;
    }
}
