package com.chatop.rentalApp_back.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DateTimeConversionUtil class provides utility methods for converting LocalDateTime objects to strings.
 * It uses the "yyyy/MM/dd" format for date conversion.
 */
public class DateTimeConversionUtil {

    /**
     * Converts a LocalDateTime object to a string using the format "yyyy/MM/dd".
     *
     * @param localDateTime LocalDateTime object to be converted.
     * @return String representation of the LocalDateTime in the specified format.
     */
    public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
        // Crée un formateur de date avec le format "yyyy/MM/dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // Utilisez le formateur pour convertir LocalDateTime en String
        String formattedDate = localDateTime.format(formatter);

        return formattedDate;
    }
}