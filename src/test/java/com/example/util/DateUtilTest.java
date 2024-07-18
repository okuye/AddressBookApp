package com.example.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest {
    @Test
    public void testParseDate() {
        LocalDate date = DateUtil.parseDate("16/03/1977");
        assertEquals(1977, date.getYear());
        assertEquals(3, date.getMonthValue());
        assertEquals(16, date.getDayOfMonth());
    }

    @Test
    public void testDaysBetween() {
        LocalDate date1 = LocalDate.of(1977, 3, 16);
        LocalDate date2 = LocalDate.of(1985, 1, 15);
        long daysBetween = DateUtil.daysBetween(date1, date2);
        assertEquals(2862, daysBetween); // Adjust to match the correct difference
    }
}
