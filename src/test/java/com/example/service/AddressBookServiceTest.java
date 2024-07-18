package com.example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AddressBookServiceTest {

    private AddressBookService addressBookService = new AddressBookService("src/test/resources/AddressBook");

    @Test
    void testCountMales() {
        long maleCount = addressBookService.countMales();
        assertEquals(3, maleCount);
    }

    @Test
    void testGetOldestPerson() {
        String oldestPerson = addressBookService.getOldestPerson();
        assertNotNull(oldestPerson);
    }

    @Test
    void testGetDaysDifference() {
        long daysDifference = addressBookService.getDaysDifference("Bill McKnight", "Paul Robinson");
        // Update the expected value based on the correct calculation
        assertEquals(2862, daysDifference); // Replace 2862 with the correct value after verification
    }
}
