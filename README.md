# CRUD in Java

It allows you to perform CRUD (Create, Read, Update, Delete) operations on a PostgreSQL database.

## Features

- Add a new user
- Retrieve all users
- Update an existing user
- Delete a user
- Retrieve a user by ID

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- PostgreSQL database
- IntelliJ IDEA or any other Java IDE

## Setup

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   cd <repository-directory>
2. **Configure the database:** 
    Update the PostgreSQL connection URL, username, and password in src/UserDAO.java:
    ```sh
      private final String jdbcURL = "jdbc:postgresql://localhost:5432/crud_db";
      private final String jdbcUsername = "";
      private final String jdbcPassword = "";

4. **Create the database and table:**
   ```sh  
    CREATE DATABASE crud_db;
    \c crud_db;
    CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        name VARCHAR(100),
        email VARCHAR(100)
    );
5. **Build and run the project:**  
    Open the project in IntelliJ IDEA.
    Build the project.
    Run the Main class.
   
