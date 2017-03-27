package org.amulyam.rbac.util;

/**
 * Created by amulyam on 27/03/17.
 */

/**
 * Utility class
 */
public class Utils {

    /**
     * Checks if a string is null or empty
     *
     * @param str String to be checked
     * @return true if string is null or empty
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Checks if string is not null or non-empty
     *
     * @param str String to be checked
     * @return true if string is both not null and non-empty
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
