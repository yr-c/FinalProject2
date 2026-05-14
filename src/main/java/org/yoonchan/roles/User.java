package org.yoonchan.roles;

import lombok.*;
import org.yoonchan.Library;
import org.yoonchan.entities.Item;
import org.yoonchan.util.Constants;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
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
     * Fetches the maximum Item borrow amount of a User subclass.
     * @return The maximum Item borrow amount of the invoked subclass.
     */
    public abstract int getMaximumBorrowAmount();

    /**
     * Adds an item to the User's borrowedItems list.
     * @param item The item to be borrowed.
     * @throws InvalidItemException If the invoked Item to borrow is null or not in the Library's itemCatalogue.
     * @throws MaximumBorrowsReachedException If the User has reached his maximum borrows.
     */
    public void borrowItem(Item item) throws InvalidItemException, MaximumBorrowsReachedException {
        if (item == null) {
            throw new InvalidItemException("Invalid item. Input is null.");
        }

        if (!Library.itemCatalogue.contains(item)) {
            throw new InvalidItemException("Invalid item. Item not found in Library's itemCatalogue.");
        }

        if (this.getBorrowedItems().size() > this.getMaximumBorrowAmount()) {
            throw new MaximumBorrowsReachedException(
                    String.format("Max items of %d reached for userId %s.", Constants.MAX_ITEMS_STUDENT, this.getId()));
        }

        switch (item.getStatus()) {
            case AVAILABLE -> {
                this.getBorrowedItems().add(item);
                item.setStatus(Item.Status.BORROWED);
            }
            case BORROWED -> {
                throw new InvalidItemException("Invalid item. Item already borrowed.");
            }
            case LOST ->
                throw new InvalidItemException("Invalid item. Item lost.");
        }
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
