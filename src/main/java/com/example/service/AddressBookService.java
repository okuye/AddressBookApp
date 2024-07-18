package com.example.service;

import com.example.model.Person;
import com.example.util.DateUtil;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AddressBookService {
    private String filePath;

    public AddressBookService() {
        this.filePath = "src/main/resources/AddressBook"; // default path for production
    }

    public AddressBookService(String filePath) {
        this.filePath = filePath; // used for testing
    }

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

    public String getOldestPerson() {
        AtomicReference<Person> oldestPerson = new AtomicReference<>();
        processFile(line -> {
            String[] details = line.split(", ");
            String name = details[0];
            String gender = details[1];
            LocalDate birthDate = DateUtil.parseDate(details[2]);
            Person person = new Person(name, gender, birthDate);

            oldestPerson.updateAndGet(currentOldest -> {
                if (currentOldest == null || person.getBirthDate().isBefore(currentOldest.getBirthDate())) {
                    return person;
                }
                return currentOldest;
            });
        });
        return oldestPerson.get() != null ? oldestPerson.get().getName() : null;
    }

    public long getDaysDifference(String name1, String name2) {
        AtomicReference<LocalDate> birthDate1 = new AtomicReference<>();
        AtomicReference<LocalDate> birthDate2 = new AtomicReference<>();

        processFile(line -> {
            String[] details = line.split(", ");
            String name = details[0];
            LocalDate birthDate = DateUtil.parseDate(details[2]);

            if (name1.equalsIgnoreCase(name)) {
                birthDate1.set(birthDate);
            } else if (name2.equalsIgnoreCase(name)) {
                birthDate2.set(birthDate);
            }
        });

        if (birthDate1.get() != null && birthDate2.get() != null) {
            return DateUtil.daysBetween(birthDate1.get(), birthDate2.get());
        }
        return 0;
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