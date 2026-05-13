package org.yoonchan;

import java.io.File;

public interface CSVPersister {
    /**
     * Initializes items and users into the Library's itemCatalogue from
     * the CSV files {@code items.csv} and {@code users.csv}. Invokes both
     * loadItemsFromCSV and loadUsersFromCSV methods.
     */
    default void loadAllFromCSV() {
        loadItemsFromCSV();
        loadUsersFromCSV();
    }

    /**
     * Initializes items into the Library's itemCatalogue from
     * the CSV file {@code items.csv}.
     */
    default void loadItemsFromCSV() {
        File file = new File(Constants.ITEMS_CSV_PATH);

    }

    /**
     * Initializes users into the Library's itemCatalogue from
     * the CSV file {@code users.csv}.
     */
    default void loadUsersFromCSV() {
        File file = new File(Constants.USERS_CSV_PATH);


    }

    /**
     * Saves items and users into the {@code items.csv} and {@code users.csv} files from
     * the Library's itemCatalogue. Invokes both
     * saveItemsToCSV and saveUsersToCSV methods.
     */
    default void saveAllToCSV() {
        saveUsersToCSV();
        saveItemsToCSV();
    }

    /**
     * Saves items from the Library's itemCatalogue to
     * the CSV file {@code items.csv}.
     */
    default void saveItemsToCSV() {
        File file = new File(Constants.ITEMS_CSV_PATH);

    }

    /**
     * Saves users from the Library's itemCatalogue to
     * the CSV file {@code users.csv}.
     */
    default void saveUsersToCSV() {
        File file = new File(Constants.USERS_CSV_PATH);

    }
}
