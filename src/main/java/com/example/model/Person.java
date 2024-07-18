package com.example.model;

import java.time.LocalDate;

public class Person {
    private String name;
    private String gender;
    private LocalDate birthDate;

    public Person(String name, String gender, LocalDate birthDate) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
