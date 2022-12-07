<h1>Delivery-API</h1>

> Status: Developing ⚠️

### Contents
  
* [What is it?](#what-is-it)
* [Requirements](#requirements)
* [Technologies Used](#technologies)
* [Installation](#installation)
* [Run Application](#run-application)
* [General Usage](#general-usage)
* [Diagrams](#uml)

## <a name="what-is-it"></a>What is it?

- Delivery-Api is a REST-API developed in Java, Spring Boot and MySQL. This API is designed to manage a back-end application for managing a food delivery enterprise.

## <a name="requirements"></a>Requirements

- Java 12+
- Spring Boot
- MySQL 8.0+
- Lombok

## <a name="technologies"></a>Technologies Used

- Java
- Spring Boot (version 2.2.2.RELEASE)
- MySQL
- Lombok
- Flyway
- Model Mapper
- Squiggly Filter For Jackson
- JasperReports
- Clean Architecture
- Data Transfer Object (DTO)
- Swagger
- Hateoas

## <a name="installation"></a>Installation

- Clone the repository for your device;
- Import it as a MAVEN project in your IDE;
- In your Postman client, import the requests from the Delivery-Api.postman_collection.json file in the Utils folder;
- In the application.properties file, edit the USER and PASSWORD of your MySQL database;
- To use the Restaurante Produto Foto service in CRUD, edit the field delivery.storage in the application.properties file. In this field insert a folder directory like this "/Users/user/Directory/folder". With this folder, the service will be able to locally store a JPEG or PNG file injected from a postman request.

### Application.properties
```xml
spring.datasource.username=
spring.datasource.password=
.
.
.
delivery.storage.location.directory-photos=
```
## <a name="run-application"></a>Run Application

- After meeting the requirements and installation, run the main method as a spring boot project in DeliveryApi.java.

## <a name="general-usage"></a>General Use

### CRUD

* After adding the requests to your Postman client and run application, you will have access to the API resources. To access the documentation of supported resources, access the swagger available at: http://localhost:8080/swagger-ui/#/

### JasperReports
* In addition to CRUD, it is possible to generate a json/pdf sales report generated with Jasper Reports.

## <a name="uml"></a> Diagrams
### Class Diagram
![Delivery-api (1)](https://user-images.githubusercontent.com/41877566/205646090-2deecb69-cdea-4300-b486-b72f0c93c9c5.jpeg)

### SQL Diagram
![Delivery_SQL_Diagram](https://user-images.githubusercontent.com/41877566/204697548-9133f597-6735-4c42-a3c8-cd66d325e1f9.png)
