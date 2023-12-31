# Dev_FullStack_Project3_Back

## Overview

This repository contains the Java Spring Boot application that serves as the back-end for our full-stack project. The project involves an Angular front-end, a Mockoon server for mock data, and a Spring Boot back-end that interacts with a MySQL database.

## Application Operation

### Requirements
- Java 17 installed on your computer
- Angular CLI version 14
- Node.js

### Pre-launch Steps
Before starting the application, make sure to perform the following steps:

1. **Mockoon Server:**
  - Ensure that the Mockoon server is up and running. (only if you work on the frontend and you want to mock the backend API )

2. **Postman Collection:**
  - Import the Postman collection provided in the "ressources\postman" directory in the front-end package.

3. **Angular Application (Front End):**
  - Launch the Angular application using the command `ng serve` in the terminal within the front-end directory.

## Database Installation

### Requirements
- MySQL installed on your computer

### Steps
To install the database:

1. **MySQL Installation:**
  - Install MySQL on your computer.

2. **Database Initialization:**
  - Execute the SQL script provided in the repository to initialize the necessary database and tables.
    - Import the SQL Script provided in the "ressources\sql" directory in the front-end package.

3. **Application Configuration:**
  - Update the `application.properties` file in the Spring Boot project with your MySQL database credentials.

4. **Application Restart:**
  - Restart the Spring Boot application.

## Swagger Documentation

Explore the API endpoints through the Swagger documentation available at [Swagger Link](http://localhost:3001/swagger-ui/index.html).
