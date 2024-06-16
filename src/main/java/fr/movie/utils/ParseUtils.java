package fr.movie.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an utils class to make safe parse
 */
public class ParseUtils {

    /**
     * Parse a String to double and return the result
     * If an error is caught, the return is 0
     * 
     * @param string String to parse
     * @return Parsed double result or 0
     */
    public static double parseDouble(String string) {
        if (!checkString(string)) {
            return 0;
        }

        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Parse a String to int and return the result
     * If an error is caught, the return is 0
     * 
     * @param string String to parse
     * @return Parsed int result or 0
     */
    public static int parseInt(String string) {
        if (!checkString(string)) {
            return 0;
        }

        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Determine if a String can be parsed by a formatter
     * @param dateString String representing a LocalDate
     * @param dateTimeFormatter DateTimeFormatter
     * @return Boolean, true if the string can be parsed by the provided formatter, false if not
     */
    public static boolean canParseToLocaldate(String dateString, DateTimeFormatter dateTimeFormatter) {
        try {
            LocalDate.parse(dateString, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Check if a string can be parsed
     * 
     * @param string String to check
     * @return Boolean, false if can not be parsed, true if it can
     */
    private static boolean checkString(String string) {
        if (string == null)
            return false;

        if (string.equals(""))
            return false;

        return true;
    }
}
