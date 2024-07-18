package com.example.service;

import com.example.model.Person;
import com.example.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddressBookServiceTest {
    private AddressBookService service;

    @BeforeEach
    public void setUp() {
        service = new AddressBookService();
    }

    @Test
    public void testCountMales() {
        assertEquals(2, service.countMales());
    }

    @Test
    public void testGetOldestPerson() {
        String oldestPerson = service.getOldestPerson();
        assertNotNull(oldestPerson);
        assertEquals("Bill", oldestPerson);
    }

    @Test
    public void testGetDaysDifference() {
        long daysDifference = service.getDaysDifference("Bill", "Paul");
        assertEquals(3103, daysDifference);
    }
}
