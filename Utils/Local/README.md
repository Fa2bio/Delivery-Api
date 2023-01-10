<h1>Delivery-API - Git Clone Installation</h1>

## Contents
  
* [Installation](#installation)
* [Run Application](#run-application)
* [General Usage](#general-usage)
* [Spring Security Example](#spring)

## <a name="installation"></a>Installation

- This version contains the mass of data inserted with aftermigrate into to database;
- Clone the repository for your device;
- Import it as a MAVEN project in your IDE;
- In your Postman client, import the requests from the Delivery-Api.postman_collection.json file available at [Delivery-Api.postman_collection.zip](https://github.com/Fa2bio/Delivery-Api/files/10377469/Delivery-Api.postman_collection.zip);
- In the application.properties file, edit the USER and PASSWORD of your MySQL database;
- To use the Restaurant Photo Product Service in CRUD, edit the field delivery.storage in the application.properties file. In this field insert a folder directory like this "/Users/user/Directory/folder". With this folder, the service will be able to locally store a JPEG or PNG file injected from a postman request;
- By default, this application runs on the port 8080.

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

- After meeting the requirements and installation, run the main method as a spring boot app in DeliveryApi.java;
- To access the documentation of supported resources, access the swagger available at: http://localhost:8080/swagger-ui/#/;
- This application contains Spring Hateoas. After launching the api, access http://localhost:8080 to access the entry point.
- This application contains Spring Security. To authenticate you'll need to make a post request at http://localhost:8080/oauth/token. This request is already prepared in the file that must be imported into the postman;
- After generating the access token, just add it as a 'Bearer Token' in your requests;
- To view an authentication example, visit [Spring Security Example](#spring).

## <a name="general-usage"></a>General Use

### CRUD

* After adding the requests to your Postman client and run application, you will have access to the API resources.

### JasperReports
* In addition to CRUD, it is possible to generate a json/pdf sales report generated with Jasper Reports.

## <a name="spring"></a> Spring Security Example
### Access Token
![1](https://user-images.githubusercontent.com/41877566/208469507-889fc9fd-8f49-448b-ada8-71a89794245a.png)
### Bearer Token
![2](https://user-images.githubusercontent.com/41877566/208469558-b17f6936-880c-4e3c-a20e-ea37875c4b93.png)

