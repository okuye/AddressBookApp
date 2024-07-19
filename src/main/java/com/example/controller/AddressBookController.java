package com.example.controller;

import com.example.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @GetMapping("/countMales")
    public long countMales() {
        return service.countMales();
    }

    @GetMapping("/oldestPerson")
    public String getOldestPerson() {
        return service.getOldestPerson();
    }

    @GetMapping("/daysDifference")
    public long getDaysDifference(@RequestParam("name1") String name1, @RequestParam("name2") String name2) {
        return service.getDaysDifference(name1, name2);
    }
}
