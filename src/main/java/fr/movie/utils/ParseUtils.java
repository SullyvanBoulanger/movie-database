package fr.movie.utils;

/**
 * Represents a utils class to make safe parse
 */
public class ParseUtils {

    /**
     * Parse a String to double and return the result
     * If an error is caught, the return is 0 
     * @param string String to parse
     * @return Parsed double result or 0
     */
    public static double parseDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Parse a String to int and return the result
     * If an error is caught, the return is 0 
     * @param string String to parse
     * @return Parsed int result or 0
     */
    public static int parseInt(String label) {
        try {
            return Integer.parseInt(label);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
