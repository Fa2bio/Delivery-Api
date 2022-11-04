<h1>Delivery-API</h1>

> Status: Developing ⚠️

### Contents
  
* [What is it?](#what-is-it)
* [Prerequisites](#prerequisites)
* [Technologies Used](#technologies)
* [Installation](#installation)
* [Run Application](#run-application)
* [General Usage](#general-usage)

## <a name="what-is-it"></a>What is it?

Delivery-Api is a REST-API developed in Java and Spring Boot. This API is designed to manage a back-end application for managing a food delivery enterprise.

## <a name="prerequisites"></a>Requirements

- Java 12+
- Spring Boot (version 2.1.7.RELEASE)
- MySQL 8.0+
- Lombok

## <a name="technologies"></a>Technologies Used

- Java
- Spring Boot
- MySQL
- Lombok
- Flyway
- Model Mapper
- Squiggly Filter For Jackson
- JasperReports

## <a name="installation"></a>Installation

- Clone the repository for your device
- Import it as a MAVEN project
- In the application.properties file, edit the USER and PASSWORD of your MySQL database

### Application.properties
```xml
spring.datasource.username=
spring.datasource.password=
```
## <a name="run-application"></a>Run Application

After meeting the prerequisites and installation, run the main method as a spring boot project in DeliveryApi.java 

## <a name="general-usage"></a>General Usage

In addition to CRUD, it is possible to generate a pdf sales report generated with Jasper Reports 
