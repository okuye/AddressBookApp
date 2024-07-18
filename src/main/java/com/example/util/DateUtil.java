package com.example.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final DateTimeFormatter DATE_FORMATTER_YY = DateTimeFormatter.ofPattern("dd/MM/yy");
    private static final DateTimeFormatter DATE_FORMATTER_YYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DATE_FORMATTER_YYYY);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(date, DATE_FORMATTER_YY);
        }
    }

    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }
}
