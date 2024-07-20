package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final DateTimeFormatter DATE_FORMATTER_YY = DateTimeFormatter.ofPattern("dd/MM/yy");
    private static final DateTimeFormatter DATE_FORMATTER_YYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DATE_FORMATTER_YYYY);
        } catch (DateTimeParseException e) {
            logger.warn("Failed to parse date with format yyyy: {}. Attempting with format yy.", date);
            try {
                return LocalDate.parse(date, DATE_FORMATTER_YY);
            } catch (DateTimeParseException ex) {
                logger.error("Failed to parse date: {}", date, ex);
                throw new RuntimeException("Failed to parse date: " + date, ex);
            }
        }
    }

    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        logger.info("Days between {} and {}: {}", startDate, endDate, days);
        return days;
    }
}
