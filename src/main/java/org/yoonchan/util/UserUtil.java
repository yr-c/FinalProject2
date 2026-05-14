package org.yoonchan.util;

import org.yoonchan.entities.Book;
import org.yoonchan.entities.DVD;
import org.yoonchan.entities.Item;
import org.yoonchan.entities.Magazine;
import org.yoonchan.roles.User;

public class UserUtil {
    /**
     * A stateless helper method that efficiently parses csv lines of User data while taking into account
     * commas present inside of square brackets specifically. The line of data must strictly be of the form
     * {@code id,name,[item1;item2;...]}.
     * <p>
     * This will split a String of User CSV data into multiple elements conforming to the fields of User.
     * <p>
     * Ex. "id,name,[item1;item2]" -> {id, name, {item1, item2}}
     * @param line The input line of data.
     * @return The elements conforming to the fields of User.
     */
    public static String[] splitUserCSVData(String line) {
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
     * @throws IllegalArgumentException If the input user is null or has item of unexpected type.
     */
    public static String userDataToCSVString(User user) throws IllegalArgumentException {
        if (user == null) throw new IllegalArgumentException();

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
                    sb.append(dvd.getPublisher()).append(",");
                    sb.append(dvd.getIssueNumber());
                }

                case Magazine magazine -> {
                    sb.append(magazine.getDirector()).append(",");
                    sb.append(magazine.getDurationMins());
                }

                default -> throw new IllegalArgumentException();
            }

            sb.append(";");
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.append("]\n").toString(); // Replace last semicolon
    }
}
