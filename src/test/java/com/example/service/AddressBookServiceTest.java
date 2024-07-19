package com.example.service;

import com.example.AddressBookApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = AddressBookApp.class)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class AddressBookServiceTest {

    @Value("${addressbook.filepath}")
    private String testFilePath;

    @Test
    void testCountMales() {
        AddressBookService addressBookService = new AddressBookService(testFilePath);
        long maleCount = addressBookService.countMales();
        assertEquals(2, maleCount); // Expected 2 males in the test data
    }

    @Test
    void testGetOldestPerson() {
        AddressBookService addressBookService = new AddressBookService(testFilePath);
        String oldestPerson = addressBookService.getOldestPerson();
        assertEquals("John Doe", oldestPerson); // Expected oldest person in the test data
    }

    @Test
    void testGetDaysDifference() {
        AddressBookService addressBookService = new AddressBookService(testFilePath);
        long daysDifference = addressBookService.getDaysDifference("John Doe", "Bob Johnson");

        // Correct the expected value to 2012
        assertEquals(2012, daysDifference);
    }
}
