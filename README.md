<h1>Delivery-API</h1>

> Status: Developing ⚠️

### Contents
  
* [What is it?](#what-is-it)
* [Requirements](#requirements)
* [Technologies Used](#technologies)
* [Installation](#installation)
* [Run Application](#run-application)
* [General Usage](#general-usage)

## <a name="what-is-it"></a>What is it?

Delivery-Api is a REST-API developed in Java and Spring Boot. This API is designed to manage a back-end application for managing a food delivery enterprise.

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

## <a name="installation"></a>Installation

- Clone the repository for your device
- Import it as a MAVEN project
- In the application.properties file, edit the USER and PASSWORD of your MySQL database
- In your Postman client, import requests from the Delivery-Api.postman_collection.json file

### Application.properties
```xml
spring.datasource.username=
spring.datasource.password=
```
## <a name="run-application"></a>Run Application

After meeting the requirements and installation, run the main method as a spring boot project in DeliveryApi.java 

## <a name="general-usage"></a>General Usage

In addition to CRUD, it is possible to generate a pdf sales report generated with Jasper Reports

### CRUD
<table border="1">
   <thead>
   <tr>
       <th>Entidades/Metodos</th>
       <th>Cidades</th>
       <th>Cozinhas</th>
       <th>Estados</th>
       <th>Formas De Pagamento</th>
       <th>Grupos</th>
       <th>Grupos-Permissoes</th>
       <th>Pedidos</th>
       <th>Restaurantes</th>
       <th>Restaurantes-Formas De Pagamento</th>
       <th>Restaurantes-Produtos</th>
       <th>Restaurantes-Usuarios</th>
       <th>Usuarios</th>
       <th>Usuarios-Grupos</th>
       <th>Estatisticas</th>     
   </tr>
   </thead>
   <tbody>
   <tr>
       <td>(GET) Listar</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
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
       <td>(GET) Buscar</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>❌</td>
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
       <td>(DEL) Excluir</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
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
       <td>(PUT) Atualizar</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
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
       <td>(POST) Adicionar</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>❌</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
       <td>✔️</td>
   </tr>
   </tbody>
</table>


