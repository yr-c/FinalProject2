package org.yoonchan.roles;

import lombok.*;
import org.yoonchan.Library;
import org.yoonchan.entities.Book;
import org.yoonchan.entities.DVD;
import org.yoonchan.entities.Item;
import org.yoonchan.entities.Magazine;
import org.yoonchan.util.Constants;

import java.util.*;

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
        if (item == null) {
            throw new InvalidItemException("Invalid item. Input is null.");
        }

        if (!this.getBorrowedItems().contains(item)) {
            throw new InvalidItemException("Invalid item. Item is not in user's borrowedItems list.");
        }

        this.getBorrowedItems().remove(item);
        item.setStatus(Item.Status.AVAILABLE);
    }

    /**
     * Recursively searches for an Item in the Library's itemCatalogue using a keyword, looking for a
     * title or author (case-insensitive).
     * <p>
     * If an item has more than one copy, the search result will contain only one copy of the item.
     * @return Whether the search found a match for the item.
     */
    public boolean searchItemRecursive(String keyword) {
        if (keyword == null || keyword.isEmpty()) return false;

        Set<String> seenMatches = new HashSet<>();
        List<Item> results = new ArrayList<>();

        // Start recursion at index 0, this will also print the result
        searchHelper(Library.itemCatalogue, 0, keyword.toLowerCase(), seenMatches, results);

        return !results.isEmpty();
    }

    /**
     * A helper method for the searchItemRecursive method. It will recursively check for keyword matches in the
     * Library's itemCatalogue until the end of the list, in which case it will terminate and print the results.
     * @param items The item list to search.
     * @param index The start index.
     * @param keyword The keyword to search for.
     * @param seenMatches An initialized set of seen matches.
     * @param results An initialized list of results.
     */
    private void searchHelper(List<Item> items, int index, String keyword, Set<String> seenMatches, List<Item> results) {
        if (index >= items.size()) {
            System.out.println("Found matches: \n" + results);
            return;
        }

        Item item = items.get(index);

        // STEP 1: Check if the current item matches the keyword
        boolean matches = item.getTitle().toLowerCase().contains(keyword) ||
                switch (item) {
                    case Book book -> book.getAuthor().toLowerCase().contains(keyword);
                    case DVD dvd -> dvd.getDirector().toLowerCase().contains(keyword);
                    case Magazine magazine -> false;
                    default -> false;
                };

        // STEP 2: If it matches, check for duplicates and add to results
        if (matches) {
            String uniqueKey = switch (item) {
                case Book book -> book.getTitle() + "|" + book.getAuthor();
                case DVD dvd -> dvd.getTitle() + "|" + dvd.getDirector();
                case Magazine magazine -> magazine.getTitle();
                default -> item.getTitle();
            };

            // .add() returns true if the key is new to the Set
            if (seenMatches.add(uniqueKey.toLowerCase())) {
                results.add(item);
            }
        }

        // RECURSIVE STEP: Call the method again for the next index
        searchHelper(items, index + 1, keyword, seenMatches, results);
    }

    /**
     * Searches using a Stream for an Item in the Library's itemCatalogue using a keyword, looking for a
     * title or author (case-insensitive).
     * <p>
     * If an item has more than one copy, the search result will contain only one copy of the item.
     * @return Whether the search found a match for the item.
     */
    public boolean searchItemStream(String keyword) {
        if (keyword == null || keyword.isEmpty()) return false;

        String finalKeyword = keyword.toLowerCase();

        Set<String> seenKeys = new HashSet<>();

        List<Item> results = Library.itemCatalogue.stream()
                .filter(item -> {
                    boolean titleMatch = item.getTitle().toLowerCase().contains(finalKeyword);

                    boolean specificMatch = switch (item) {
                        case Book book -> book.getAuthor().toLowerCase().contains(finalKeyword);
                        case DVD dvd -> dvd.getDirector().toLowerCase().contains(finalKeyword);
                        case Magazine magazine -> magazine.getPublisher().toLowerCase().contains(finalKeyword);
                        default -> false;
                    };

                    return titleMatch || specificMatch;
                })
                .filter(item -> {
                    String uniqueKey = switch (item) {
                        case Book book -> book.getTitle() + "|" + book.getAuthor();
                        case DVD dvd -> dvd.getTitle() + "|" + dvd.getDirector();
                        case Magazine magazine -> magazine.getTitle() + "|" + magazine.getPublisher();
                        default -> item.getTitle();
                    };
                    return seenKeys.add(uniqueKey.toLowerCase());
                })
                .toList();

        System.out.println("Found matches: \n" + results);
        return !results.isEmpty();
    }
}
