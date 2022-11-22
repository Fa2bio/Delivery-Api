<h1>Delivery-API</h1>

> Status: Developing ⚠️

### Contents
  
* [What is it?](#what-is-it)
* [Requirements](#requirements)
* [Technologies Used](#technologies)
* [Class Diagram](#uml)
* [Installation](#installation)
* [Run Application](#run-application)
* [General Usage](#general-usage)

## <a name="what-is-it"></a>What is it?

Delivery-Api is a REST-API developed in Java, Spring Boot and MySQL. This API is designed to manage a back-end application for managing a food delivery enterprise.

## <a name="requirements"></a>Requirements

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
- Clean Architecture

## <a name="uml"></a> Class Diagram
![Delivery-Api](https://user-images.githubusercontent.com/41877566/202730057-0e6a3cce-c6ee-43e3-9a06-0ba6ef6c2b07.jpg)

## <a name="installation"></a>Installation

- Clone the repository for your device;
- Import it as a MAVEN project in your IDE;
- In the application.properties file, edit the USER and PASSWORD of your MySQL database;
- To use the Restaurante Produto Foto service in CRUD, edit the field delivery.storage in the application.properties file. In this field insert a folder directory like this "/Users/user/Directory/folder". With this folder, the service will be able to locally store a JPEG or PNG file injected from a postman request;
- Open your Postman client and import requests from the Delivery-Api.postman_collection.json file.

### Application.properties
```xml
spring.datasource.username=
spring.datasource.password=
.
.
.
delivery.storage.local.diretorio-fotos=
```
## <a name="run-application"></a>Run Application

After meeting the requirements and installation, run the main method as a spring boot project in DeliveryApi.java.

## <a name="general-usage"></a>General Use

### CRUD

* After you add the requests to your Postman client, you'll have access to the following API features.

<table border="1">
   <thead>
   <tr>
       <th>Entities/Methods</th>
       <th>Cidades</th>
       <th>Cozinhas</th>
       <th>Estados</th>
       <th>Formas De Pagamento</th>
       <th>Grupos</th>
       <th>Grupos-Permissoes</th>
       <th>Pedidos</th>
       <th>Restaurantes</th>
   </tr>
   </thead>
   <tbody>
   <tr>
       <td>(GET) List</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
   </tr>
   <tr>
       <td>(GET) Find</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>❌</td>
       <td>✔️</td>
       <td>✔️</td>
   </tr>
   
   <tr>
       <td>(DEL) Delete</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
   </tr>
     
   <tr>
       <td>(PUT) Update</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>❌</td>
       <td>❌</td>
       <td>✔️</td>
   </tr>
     
   <tr>
       <td>(POST) Add</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>❌</td>
       <td>✔️</td>
       <td>✔️</td>
   </tr>

   <tr>
       <td>(PUT) Associate</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>  
       <td>❌</td>
       <td>✔️</td>
       <td>❌</td>
       <td>❌</td>
   </tr>

   <tr>
       <td>(DEL) Disassociate</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>✔️</td>
       <td>❌</td>
       <td>❌</td>
   </tr>

   <tr>
       <td>(PUT) Flow</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>       
       <td>❌</td>
       <td>✔️</td>
       <td>❌</td>
   </tr>

   <tr>
       <td>(PUT) Opening</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>✔️</td>
   </tr>

   <tr>
       <td>(PUT) Activate</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>✔️</td>
   </tr>

   <tr>
       <td>(PUT) Closening</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>✔️</td>
   </tr>

   <tr>
       <td>(PUT) Deactivate</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>❌</td>
       <td>✔️</td>
   </tr>

   </tbody>
</table>

<table border="2">
   <thead>
   <tr>
       <th>Entities/Methods</th>
       <th>Restaurantes-Formas De Pagamento</th>
       <th>Restaurantes-Produtos</th>
       <th>Restaurantes-Usuarios</th>
       <th>Restaurantes-Produto Foto</th>
       <th>Usuarios</th>
       <th>Usuarios-Grupos</th>
   </tr>
   </thead>
   <tbody>
   <tr>
       <td>(GET) List</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>❌</td>
       <td>✔️</td>
       <td>✔️</td>
   </tr>
   <tr>
       <td>(GET) Find</td>
       <td>❌</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>❌</td>
   </tr>
   
   <tr>
       <td>(DEL) Delete</td>
       <td>❌</td>
       <td>❌</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>❌</td>
   </tr>
     
   <tr>
       <td>(PUT) Update</td>
       <td>❌</td>
       <td>✔️</td>
       <td>❌</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>❌</td>
   </tr>
     
   <tr>
       <td>(POST) Add</td>
       <td>❌</td>
       <td>✔️</td>
       <td>❌</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
   </tr>

   <tr>
       <td>(PUT) Associate</td>
       <td>✔️</td>       
       <td>❌</td>
       <td>✔️</td>
       <td>❌</td>
       <td>❌</td>
       <td>✔️</td>
   </tr>
   
   <tr>
       <td>(DEL) Disassociate</td>
       <td>✔️</td>
       <td>❌</td>
       <td>✔️</td>
       <td>❌</td>
       <td>❌</td>
       <td>✔️</td>
   </tr>

   </tbody>
</table>

### JasperReports
* In addition to CRUD, it is possible to generate a json/pdf sales report generated with Jasper Reports.

<table border="1">
   <thead>
   <tr>
       <th>Entities/Methods</th>
       <th>Statistics</th>
   </tr>
   </thead>
   <tbody>
   <tr>
       <td>(GET) Daily Sales - Json</td>
        <td>✔️</td>
   </tr>
   <tr>
        <td>(GET) Daily Sales - PDF</td>
        <td>✔️</td>
       
   </tr>
   </tbody>
</table>
