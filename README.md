# `Patient Management System`

A Spring Boot application for managing `Patient`, including adding, updating, deleting, and fetching Patient details. follows all the CRUD Operations and primerily focus area of this project is [UNIT TESTING]((https://github.com/ImCodeHub/PatientManagementSystem/blob/main/README.md#testing)) and Global Exception handling with Junit and mockito.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [To contact me](#To-contact-me)

## Introduction 
  The Patient Management System is a RESTful web service built using Spring Boot. It provides APIs to manage doctor information, including operations like adding, updating, deleting, and fetching doctor details.

## Features
- Add a new Patient
- Update existing Patient details
- Delete a Patient by ID
- Fetch all Patients
- Find a Patient by ID

## Technologies Used
- Java 11
- Spring Boot 2.7.x
- Spring Data JPA
- H2 Database
- Mockito
- JUnit 5

## Setup and Installation

### Prerequisites
- Java 11 or higher
- Maven 3.6.3 or higher

use [Spring Initializr](https://start.spring.io/) web interface to generate the project.

![image](https://github.com/ImCodeHub/DoctorManagementSystemDemo/assets/98458146/2349feec-7e2c-4deb-80e4-d0840273d514)

- *Application Properties:*
  Configure `application.properties` for `H2 database:
  properties`
  spring.datasource.url=jdbc:h2:mem:testdb
  
  spring.datasource.driverClassName=org.h2.Driver
  
  spring.datasource.username=sa
  
  spring.datasource.password=password
  
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  
  spring.h2.console.enabled=true

  *add this dependency for H2 database in your [pom.xml](https://github.com/ImCodeHub/DoctorManagementSystemDemo/blob/main/pom.xml)*
   ```xml
   <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
   </dependency>
   ```

  you can change the database from H2 to MySQL H2 is inmemory database (ideal for testing) 

     ```properties
      spring.application.name=Employee
      # H2 database
      spring.datasource.url=jdbc:h2:mem:testdb
      spring.datasource.driverClassName=org.h2.Driver
      spring.datasource.username=sa
      spring.datasource.password=password
      spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
      spring.h2.console.enabled=true
     ```

Use this link to [http://localhost:8080/h2-console](http://localhost:8080/h2-console) to access H2 inmemory database.


The application will start on http://localhost:8080.

## Usage
You can use tools like [Postman](https://www.postman.com/) to interact with the API endpoints and API testing. 

![image](https://github.com/ImCodeHub/DoctorManagementSystemDemo/assets/98458146/654c45d5-ba10-44b8-a922-d544b7e640cc)

# API Endpoints
### Create a new patient
  - URL: /api/v1/savePatient
  - Method: `POST`
    
### Retrieve all patients
  - URL: /api/v1/patients
  - Method:`GET`
  
### Retrieve a patient by ID
  - URL: /api/v1/patient/{id}
  - Method: `GET`
### Update patient information
  - URL: /api/v1/update/{id}
  - Method: `PUT`

### Delete a patient by ID
  - URL: /api/v1/delete/{id}
  - Method: `DELETE`

 ### API Documentation:
   - Use `Swagger/OpenAPI` for API documentation you can follow this link http://localhost:8080/swagger-ui/index.html#/.
   - Add the `springdoc-openapi-ui` library to the list of your project dependencies (No additional configuration is needed):

      ```xml 
         <dependency>
               <groupId>org.springdoc</groupId>
               <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
               <version>2.5.0</version>
         </dependency>

      ```
      
![image](https://github.com/ImCodeHub/DoctorManagementSystemDemo/assets/98458146/2ca02b92-a077-41d9-a6ff-4a29ba2d14ed)

#### Unit testing screen short
### Testing
![Screenshot (1160)](https://github.com/ImCodeHub/DoctorManagementSystemDemo/assets/98458146/3dc10fa2-0fd4-4aba-a3dc-468307c8eb83)

#### You can see the Unit testing code at [Click Here](https://github.com/ImCodeHub/PatientManagementSystem/blob/main/PatientManagementSystem/src/test/java/com/Patient/PatientManagementSystem/Service/implement/PatientServiceImplTest.java)

```java
    public class PatientServiceImplTest {
    
        @Mock
        private PatientRepository patientRepository;
    
        @Mock
        private Validator validator;
    
        @InjectMocks
        private PatientServiceImpl patientServiceImpl;
    
        Patient patient;
    
        AutoCloseable autoCloseable;
    
        @BeforeEach
        void setUp() {
            autoCloseable = MockitoAnnotations.openMocks(this);
            patient = new Patient();
            patient.setFirstName("Ankit");
            patient.setLastName("sharma");
            patient.setDateOfBirth(LocalDate.of(1993, 11, 24));
            patient.setGender("male");
            patient.setContactNumber("1236547894");
            patient.setEmail("ankit@gmail.com");
            patient.setAddress("Indore");
    
        }
    
        @AfterEach
        void tearDown() throws Exception {
            autoCloseable.close();
        }
    
        @Test
        void testSavePatient() {
            String email = patient.getEmail();
    
            when(validator.isValidEmail(email)).thenReturn(true);
            when(validator.isEmailUnique(email)).thenReturn(true);
            when(patientRepository.save(patient)).thenReturn(patient);
    
            String result = patientServiceImpl.savePatient(patient);
            assertEquals("patient saved successfully.", result);
            verify(patientRepository, times(1)).save(patient);
        }
    }
```
---
# To contact me:
   - name: Ankit sharma
   - mobile no: 8962780856
   - E-mail id: ankitsharma.as420@gmail.com
   - My [Linked In](https://www.linkedin.com/in/ankit-sharma-a6689b1a5/) Profile.
     
**To see My other projects** [click here](https://github.com/ImCodeHub?tab=repositories)

