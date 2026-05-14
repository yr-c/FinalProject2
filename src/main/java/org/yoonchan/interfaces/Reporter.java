package org.yoonchan.interfaces;

import org.yoonchan.Library;
import org.yoonchan.entities.Item;

public interface Reporter {
    /**
     * Generates a report of all available/borrowed/lost Items from the Library's itemCatalogue.
     * Prints out a formatted list of each item, its type, and its status.
     */
    default void generateReport() {
        StringBuilder sbAvailableItems = new StringBuilder();
        StringBuilder sbBorrowedItems = new StringBuilder();
        StringBuilder sbLostItems = new StringBuilder();

        for (Item item : Library.itemCatalogue) {
            switch (item.getStatus()) {
                case AVAILABLE -> sbAvailableItems.append("- ").append(item).append("\n");
                case BORROWED -> sbBorrowedItems.append("- ").append(item).append("\n");
                case LOST -> sbLostItems.append("- ").append(item).append("\n");
            }
        }

        System.out.printf("Available items:\n%s", sbAvailableItems);
        System.out.printf("Borrowed items:\n%s", sbBorrowedItems);
        System.out.printf("Lost items:\n%s", sbLostItems);
    }
}
