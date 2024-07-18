package com.example.service;

import com.example.AddressBookApp.service.AddressBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}