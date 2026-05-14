package org.yoonchan.roles;

public interface Reporter {
    /**
     * Generates a report of all borrowed/in-store/lost Items from the Library's itemCatalogue.
     * Prints out a formatted list of each item, its type, and its status.
     */
    default void generateReport() {
        System.out.println();
    }
}
