package com.example.service;

import com.example.model.Gender;
import com.example.model.Person;
import com.example.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AddressBookService {

    private static final Logger logger = LoggerFactory.getLogger(AddressBookService.class);

    private final String filePath;

    public AddressBookService(@Value("${addressbook.filepath}") String filePath) {
        this.filePath = filePath;
    }

    public long countMales() {
        AtomicLong maleCount = new AtomicLong();
        processFile(line -> {
            String[] details = line.split(", ");
            Gender gender = Gender.fromString(details[1]);
            if (gender == Gender.MALE) {
                maleCount.incrementAndGet();
            }
        });
        logger.info("Counted {} males in the address book.", maleCount.get());
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
        String oldestName = oldestPerson.get() != null ? oldestPerson.get().getName() : null;
        logger.info("The oldest person in the address book is {}.", oldestName);
        return oldestName;
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

        // Debugging logs
        logger.debug("Processed names in address book: Name1 = {}, BirthDate1 = {}, Name2 = {}, BirthDate2 = {}",
                name1, birthDate1.get(), name2, birthDate2.get());

        if (birthDate1.get() == null || birthDate2.get() == null) {
            String errorMessage = "One or both names not found in the address book.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        long daysBetween = DateUtil.daysBetween(birthDate1.get(), birthDate2.get());
        logger.info("The days difference between {} and {} is {} days.", name1, name2, daysBetween);
        return daysBetween;
    }

    private void processFile(Processor processor) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                processor.process(line);
            }
        } catch (IOException e) {
            logger.error("Failed to read the address book file.", e);
            throw new RuntimeException("Failed to read the address book file.", e);
        }
    }

    @FunctionalInterface
    private interface Processor {
        void process(String line);
    }
}
