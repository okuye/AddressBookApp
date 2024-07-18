package com.example.AddressBookApp.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AddressBookService {
    private String filePath = "AddressBook"; // Can be configured via application.properties

    public long countMales() {
        AtomicLong maleCount = new AtomicLong();
        processFile(line -> {
            String[] details = line.split(", ");
            if ("Male".equalsIgnoreCase(details[1])) {
                maleCount.incrementAndGet();
            }
        });
        return maleCount.get();
    }

    private void processFile(Processor processor) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                processor.process(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    private interface Processor {
        void process(String line);
    }
}