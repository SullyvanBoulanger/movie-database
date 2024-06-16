package fr.movie.utils;

/**
 * Represents an util class to make safe method from String
 */
public class StringUtils {
    public static String trim(String string) {
        if (string == null)
            return null;

        return string.trim();
    }
}
