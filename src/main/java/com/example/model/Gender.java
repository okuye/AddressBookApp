package com.example.model;

public enum Gender {
    MALE, FEMALE;

    public static Gender fromString(String gender) {
        if (gender == null) {
            return null;
        }
        switch (gender.toUpperCase()) {
            case "MALE":
                return MALE;
            case "FEMALE":
                return FEMALE;
            default:
                throw new IllegalArgumentException("Unknown gender: " + gender);
        }
    }
}
