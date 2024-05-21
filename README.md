# Transaction Routine Service

## Overview

This project is a Spring Boot application that provides a REST API for managing transactions. 
It includes APIs for creating and fetching accounts, operation types and transactions with proper validation and error handling.

## Features

- Create a Account
- Fetch a Account by its ID
- Create a Transaction
- Validation for incoming requests
- Error handling

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database
- Lombok
- JUnit 5
- Mockito
- Jackson

## Getting Started

### Prerequisites

Before you begin, ensure you have the following tools installed:

- Java 11 or higher
- Maven or Gradle
- Git

### Clone the Repository

```sh
git clone https://github.com/your-username/txnroutine.git
cd txnroutine
```

### Setup the Project

### Build and Run using Docker

```sh
./build_run.sh
```

# Using Maven

- Build the application
```sh
./mvnw clean install
```
- Run the application
```sh
./mvnw spring-boot:run
```
- Run the test
```sh
./mvnw test
```

# Using Gradle

- Build the application
```sh
./gradlew clean build
```
- Run the application
```sh
./gradlew bootRun
```
- Run the test
```sh
./gradlew test
```

### API Swagger Document

Run the application and open the link - http://localhost:8080/swagger-ui.html

### In Memory H2 Database Console

Run the application and open the link - http://localhost:8080/h2-console
Enter the below details
- jdbc driver - jdbc:h2:mem:pismo
- username - pismo
- password - pismo

## License

This project is licensed under the [MIT License](LICENSE).

