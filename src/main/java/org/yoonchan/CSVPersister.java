package org.yoonchan;

import org.yoonchan.entities.*;
import org.yoonchan.roles.*;
import org.yoonchan.util.Constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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

        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Item item : Library.itemCatalogue) {
                String[] data = new String[7];
                data[0] = item.getId();
                data[1] = item.getTitle();
                data[2] = item.getStatus().toString();

                switch (item) {
                    case Book book -> {
                        data[3] = "Book";
                        data[4] = book.getISBN();
                        data[5] = book.getAuthor();
                        data[6] = book.getGenre().toString();
                    }

                    case DVD dvd -> {
                        data[3] = "DVD";
                        data[4] = dvd.getPublisher();
                        data[5] = dvd.getIssueNumber() + "";
                        data[6] = "";
                    }

                    case Magazine magazine -> {
                        data[3] = "Magazine";
                        data[4] = magazine.getDirector();
                        data[5] = magazine.getDurationMins() + "";
                        data[6] = "";
                    }

                    default -> Arrays.fill(data, "");
                }

                fileWriter.write(String.format("%s, %s, %s, %s, %s, %s, %s\n",
                        data[0], data[1], data[2], data[3], data[4], data[5], data[6]));
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Saves users from the Library's itemCatalogue to
     * the CSV file {@code users.csv}.
     */
    default void saveUsersToCSV() {
        File file = new File(Constants.USERS_CSV_PATH);

        try (FileWriter fileWriter = new FileWriter(file)) {
            for (User user : Library.registeredUsers) {
                String[] data = new String[4];
                data[0] = user.getId();
                data[1] = user.getName();
                data[2] = user.getBorrowedItems().toString();

                switch (user) {
                    case Admin admin -> {
                        data[3] = "Admin";
                    }

                    case Student student -> {
                        data[3] = "Student";
                    }

                    case Teacher teacher -> {
                        data[3] = "Teacher";
                    }

                    default -> Arrays.fill(data, "");
                }

                fileWriter.write(String.format("%s, %s, %s, %s\n",
                        data[0], data[1], data[2], data[3]));
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
