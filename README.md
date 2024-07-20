# AddressBookApp

## Overview

AddressBookApp is a Spring Boot application designed to manage an address book and answer specific queries about the data contained within. It provides RESTful endpoints to count the number of males, find the oldest person, and calculate the days difference between the birth dates of two people.

## Project Structure

```
AddressBookApp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           ├── AddressBookApp.java
│   │   │           ├── controller/
│   │   │           │   └── AddressBookController.java
│   │   │           ├── exception/
│   │   │           │   └── ApiExceptionHandler.java
│   │   │           ├── model/
│   │   │           │   ├── Gender.java
│   │   │           │   └── Person.java
│   │   │           ├── service/
│   │   │           │   └── AddressBookService.java
│   │   │           └── util/
│   │   │               └── DateUtil.java
│   ├── resources/
│   │   └── application.properties
├── test/
│   └── java/
│       └── com/
│           └── example/
│               ├── service/
│               │   └── AddressBookServiceTest.java
│               └── util/
│                   └── DateUtilTest.java
├── AddressBook
└── pom.xml
```

## Requirements

- Java 17
- Maven 3.6+
- Spring Boot 3.3.1

## Setup

1. **Clone the repository:**

   ```sh
   git clone <repository-url>
   cd AddressBookApp
   ```

2. **Build the project:**

   ```sh
   mvn clean install
   ```

3. **Run the application:**

   ```sh
   mvn spring-boot:run
   ```

## Endpoints

1. **Count Males**

   - **URL:** `/addressbook/countMales`
   - **Method:** `GET`
   - **Description:** Returns the number of males in the address book.

   ```sh
   curl http://localhost:8080/addressbook/countMales
   ```

2. **Get Oldest Person**

   - **URL:** `/addressbook/oldestPerson`
   - **Method:** `GET`
   - **Description:** Returns the name of the oldest person in the address book.

   ```sh
   curl http://localhost:8080/addressbook/oldestPerson
   ```

3. **Get Days Difference Between Two People**

   - **URL:** `/addressbook/daysDifference`
   - **Method:** `GET`
   - **Parameters:** `name1` (required), `name2` (required)
   - **Description:** Returns the number of days difference between the birth dates of the two specified people.

   ```sh
   curl "http://localhost:8080/addressbook/daysDifference?name1=Bill%20McKnight&name2=Paul%20Robinson"
   ```

## Configuration

- **application.properties:**

  The application's properties are configured in the `application.properties` file located in the `resources` directory.

  ```properties
  addressbook.filepath=AddressBook
  ```

## Testing

To run the tests, use the following command:

```sh
mvn test
```

## Project Details

- **AddressBookApp.java:** Main application class to bootstrap the Spring Boot application.
- **AddressBookController.java:** Controller class to handle HTTP requests.
- **ApiExceptionHandler.java:** Exception handler class for handling specific exceptions and returning appropriate HTTP responses.
- **Gender.java:** Enum representing gender.
- **Person.java:** Model class representing a person in the address book.
- **AddressBookService.java:** Service class containing the business logic.
- **DateUtil.java:** Utility class for date parsing and calculation.
- **AddressBookServiceTest.java:** Unit tests for `AddressBookService`.
- **DateUtilTest.java:** Unit tests for `DateUtil`.

By following these steps and utilizing the provided structure, you can effectively manage and query the address book data. The application ensures efficient handling of large datasets while providing clear and concise endpoints for interacting with the data.