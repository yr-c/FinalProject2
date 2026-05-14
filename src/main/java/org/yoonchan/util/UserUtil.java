package org.yoonchan.util;

import org.yoonchan.entities.Book;
import org.yoonchan.entities.DVD;
import org.yoonchan.entities.Item;
import org.yoonchan.entities.Magazine;
import org.yoonchan.roles.User;

public class UserUtil {
    /**
     * A stateless helper method that parses a CSV line of User data into exactly three components:
     * ID, Name, and the raw bracketed string of items.
     * <p>
     * The line of data must strictly follow the format: {@code id,name,[item1;item2;...]}.
     * Because the list of items is the final element, this method splits the string at the first
     * two commas and leaves the remainder (the bracketed list) intact as a single string.
     * <p>
     * Ex. "id,name,[item1;item2]" -> {"id", "name", "[item1;item2]"}
     * @param line The input line of CSV data.
     * @return A String array of size 3 containing the ID, Name, and the unparsed bracketed list of items.
     * @throws NullPointerException if the input line is null.
     * @throws IllegalArgumentException if the input line is empty, does not contain an opening bracket,
     *                                  or does not end with a closing bracket.
     */
    public static String[] splitUserCSVData(String line) throws IllegalArgumentException, NullPointerException {
        if (line == null) {
            throw new NullPointerException("Input line is null.");
        } else if (line.isEmpty()) {
            throw new IllegalArgumentException("Input line is empty.");
        }

        if (!line.endsWith("]")) {
            throw new IllegalArgumentException("Malformed CSV: Line must end with closing bracket.");
        }

        int openBracket = line.indexOf('[');
        if (openBracket == -1) {
            throw new IllegalArgumentException("Malformed CSV: Missing opening bracket.");
        }

        // Splits into max 3 pieces, ignoring commas inside brackets
        return line.split(",", 3);
    }

    /**
     * A stateless helper method to convert user data into an easily parseable single String.
     * @param user The user from which the data is processed.
     * @return The processed String.
     * @throws NullPointerException If the input line is null.
     * @throws IllegalArgumentException If the input user is null or has item of unexpected type.
     */
    public static String userDataToCSVString(User user) throws IllegalArgumentException, NullPointerException {
        if (user == null) throw new NullPointerException();

        StringBuilder sb = new StringBuilder();

        sb.append(user.getId()).append(",");
        sb.append(user.getName()).append(",");
        sb.append("[");

        for (Item item : user.getBorrowedItems()) {
            sb.append(item.getId()).append(",");
            sb.append(item.getTitle()).append(",");
            sb.append(item.getStatus()).append(",");

            switch (item) {
                case Book book -> {
                    sb.append(book.getIsbn()).append(",");
                    sb.append(book.getAuthor()).append(",");
                    sb.append(book.getGenre());
                }

                case DVD dvd -> {
                    sb.append(dvd.getDirector()).append(",");
                    sb.append(dvd.getDurationMins());
                }

                case Magazine magazine -> {
                    sb.append(magazine.getPublisher()).append(",");
                    sb.append(magazine.getIssueNumber());
                }

                default -> throw new IllegalArgumentException();
            }

            sb.append(";");
        }

        if (!user.getBorrowedItems().isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.append("]\n").toString(); // Replace last semicolon
    }
}
