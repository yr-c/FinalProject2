package org.yoonchan.util;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {
    /**
     * Checks whether an input code String conforms to the ISBN-13 standard (with possible hyphens included in the input).
     * This performs a length check and verifies the check digit of the code.
     * <p>
     * This check digit must range from 0 to 9 and must be such that the sum of all the thirteen digits, each multiplied
     * by its integer weight, alternating between 1 and 3, is a multiple of 10. Additionally, the code must begin with
     * the digits 978 or 979, and contain exactly 13 digits.
     * @param code The input code String.
     * @return Whether the ISBN is valid.
     */
    public static boolean isISBNValid(String code) {
        if (code == null) throw new NullPointerException("Input ISBN code is null.");

        int standardCodeLenWithoutDashes = 13;
        if (code.length() < standardCodeLenWithoutDashes) return false;

        if (!(code.startsWith("978") || code.startsWith("979"))) return false;

        List<Integer> digits = new ArrayList<>(13);
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (Character.isDigit(c)) {
                digits.add(Character.getNumericValue(c));
            }
        }

        if (digits.size() != 13) return false; // Early exit; there must be 13 digits without hyphens.

        int sum = 0;
        for (int i = 0; i < digits.size() - 1; i++) {
            int digit = digits.get(i);

            if (i % 2 == 0) {
                sum += digit;
            } else {
                sum += digit * 3;
            }
        }

        sum %= 10;

        int expectedCheckDigit = 10 - sum;
        int givenCheckDigit = digits.getLast();

        return expectedCheckDigit == givenCheckDigit;
    }
}
