package org.yoonchan.interfaces;

import org.yoonchan.Library;
import org.yoonchan.entities.*;
import org.yoonchan.roles.Admin;
import org.yoonchan.roles.Student;
import org.yoonchan.roles.Teacher;
import org.yoonchan.roles.User;
import org.yoonchan.util.Constants;
import org.yoonchan.util.ItemUtil;
import org.yoonchan.util.UserUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        Library.itemCatalogue.clear();
        File file = new File(Constants.ITEMS_CSV_PATH);
        int bookNum = 0;
        int dvdNum = 0;
        int magazineNum = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] elements = line.split(",");

                String id = elements[0];
                String title = elements[1];
                Item.Status status = Item.Status.valueOf(elements[2]);

                switch (id.charAt(0)) {
                    case 'B' -> {
                        String isbn = elements[3];
                        String author = elements[4];
                        String genre = elements[5];

                        Item book = new Book(id, title, status, isbn, author, genre);
                        Library.itemCatalogue.add(book);
                        bookNum++;
                    }

                    case 'M' -> {
                        String publisher = elements[3];
                        int issueNumber = Integer.parseInt(elements[4]);

                        Item dvd = new DVD(id, title, status, publisher, issueNumber);
                        Library.itemCatalogue.add(dvd);
                        dvdNum++;
                    }

                    case 'D' -> {
                        String director = elements[3];
                        int durationMins = Integer.parseInt(elements[4]);

                        Item magazine = new Magazine(id, title, status, director, durationMins);
                        Library.itemCatalogue.add(magazine);
                        dvdNum++;
                    }

                    default -> {
                        System.out.println("CRITICAL: Malformed data at line: " + line + "\n(Missing Item type)");
                        throw new RuntimeException();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }

        Book.setNextId(bookNum + 1);
        DVD.setNextId(dvdNum + 1);
        Magazine.setNextId(magazineNum + 1);
    }

    /**
     * Initializes users into the Library's itemCatalogue from
     * the CSV file {@code users.csv}.
     */
    default void loadUsersFromCSV() {
        Library.registeredUsers.clear();
        File file = new File(Constants.USERS_CSV_PATH);

        int adminNum = 0;
        int studentNum = 0;
        int teacherNum = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] elements = UserUtil.splitUserCSVData(line);

                String id = elements[0];
                String name = elements[1];

                // Splitting the long String into bits of Item
                String borrowedItemsString = elements[2];
                String strippedBorrowedItemsString =
                        borrowedItemsString.replace("[", "").replace("]", "");
                String[] itemStrings = strippedBorrowedItemsString.split(";");

                List<Item> borrowedItems = new ArrayList<>();
                for (String itemString : itemStrings) {
                    String[] itemData = itemString.split(",");
                    String itemId = itemData[0];
                    String itemTitle = itemData[1];
                    Item.Status itemStatus = Item.Status.valueOf(itemData[2]);

                    switch (itemId.charAt(0)) {
                        case 'B' -> {
                            String isbn = itemData[3];
                            String author = itemData[4];
                            String genre = itemData[5];

                            borrowedItems.add(new Book(itemId, itemTitle, itemStatus, isbn, author, genre));
                        }

                        case 'M' -> {
                            String publisher = itemData[3];
                            int issueNumber = Integer.parseInt(itemData[4]);

                            borrowedItems.add(new DVD(itemId, itemTitle, itemStatus, publisher, issueNumber));
                        }

                        case 'D' -> {
                            String director = itemData[3];
                            int durationMins = Integer.parseInt(itemData[4]);

                            borrowedItems.add(new Magazine(itemId, itemTitle, itemStatus, director, durationMins));
                        }

                        default -> {
                            System.out.println("CRITICAL: Malformed data at line: " + line + "\n(Unexpected ID header)");
                            throw new RuntimeException();
                        }
                    }
                }

                switch (id.charAt(0)) {
                    case 'A' -> {
                        Library.registeredUsers.add(new Admin(id, name, borrowedItems));
                        adminNum++;
                    }

                    case 'S' -> {
                        Library.registeredUsers.add(new Student(id, name, borrowedItems));
                        studentNum++;
                    }

                    case 'T' -> {
                        Library.registeredUsers.add(new Teacher(id, name, borrowedItems));
                        studentNum++;
                    }

                    default -> {
                        System.out.println("CRITICAL: Malformed data at line: " + line + "\n(Unexpected ID header)");
                        throw new RuntimeException();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }

        Admin.setNextId(adminNum + 1);
        Student.setNextId(studentNum + 1);
        Teacher.setNextId(teacherNum + 1);
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

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            for (Item item : Library.itemCatalogue) {
                fileWriter.write(ItemUtil.itemDataToCSVString(item));
            }

        } catch (IOException e) {
            throw new RuntimeException();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves users from the Library's itemCatalogue to
     * the CSV file {@code users.csv}.
     */
    default void saveUsersToCSV() {
        File file = new File(Constants.USERS_CSV_PATH);

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            for (User user : Library.registeredUsers) {
                if (user.getName().equalsIgnoreCase("system")) continue; // Ignore saving system admins
                fileWriter.write(UserUtil.userDataToCSVString(user));
            }

        } catch (IOException e) {
            throw new RuntimeException();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
