<h1>Delivery-API - Docker Installation</h1>

### Contents
  
* [Installation](#installation)
* [Run Application](#run-application)
* [General Usage](#general-usage)
* [Spring Security Example](#spring)

## <a name="installation"></a>Installation

<h3> Let's start by preparing the Docker Images. Open a terminal and type </h3>

### Terminal
```xml
docker pull mysql 
```

```xml
docker pull fabioscp/delivery_api
```

<h3> Now let's prepare the Docker Containers. Open a terminal and type </h3>

### Terminal
```xml
docker run -p 3307:3306 --name dbname -e MYSQL_ROOT_PASSWORD=password -d mysql
```

```xml
docker network create spring-net
```

```xml
docker network connect spring-net dbname
```

```xml
docker docker container inspect dbname
```

- After starting the docker inspection, look for the generated IPAddress and copy it;
- Check if the spring-net network is present;
- Open your MySQL Workbench and start a new localhost connection with the generated IPAddress, port 3307 and root user;
- In your Postman client, import the requests from the Delivery-Api-Docker.postman_collection.json file available at [Delivery-Api-Docker.postman_collection.zip](https://github.com/Fa2bio/Delivery-Api/files/10377682/Delivery-Api-Docker.postman_collection.zip);
- To use the Restaurant Photo Product Service in CRUD, edit the field delivery.storage in the application.properties file. In this field insert a folder directory like this "/Users/user/Directory/folder". With this folder, the service will be able to locally store a JPEG or PNG file injected from a postman request;
- This version does not contain the mass of data inserted with aftermigrate into the database. To test endpoints, you will need to enter data manually;
- By default, this application runs on the port 9090.

## <a name="run-application"></a>Run Application

- After meeting the requirements and installation, open a terminal and type

### Terminal
```xml
docker run -p 9090:8080 --name fabioscp/delivery_api --net spring-net -e MYSQL_HOST=dbname -e MYSQL_USER=root -e MYSQL_PASSWORD=password -e MYSQL_PORT=3306 fabioscp/delivery_api

```

- To access the documentation of supported resources, access the swagger available at: http://localhost:9090/swagger-ui/#/;
- This application contains Spring Hateoas. After launching the api, access http://localhost:9090 to access the entry point.
- This application contains Spring Security. To authenticate you'll need to make a post request at http://localhost:9090/oauth/token. This request is already prepared in the file that must be imported into the postman;
- After generating the access token, just add it as a 'Bearer Token' in your requests;
- To view an authentication example, visit [Spring Security Example](#spring).

## <a name="general-usage"></a>General Use

### CRUD

* After adding the requests to your Postman client and run application, you will have access to the API resources.

### JasperReports
* In addition to CRUD, it is possible to generate a json/pdf sales report generated with Jasper Reports.

## <a name="spring"></a> Spring Security Example
### Access Token
![2](https://user-images.githubusercontent.com/41877566/211430700-83c8b996-115f-48d9-ad9b-8d755af2abf9.png)
### Bearer Token
![2](https://user-images.githubusercontent.com/41877566/208469558-b17f6936-880c-4e3c-a20e-ea37875c4b93.png)

