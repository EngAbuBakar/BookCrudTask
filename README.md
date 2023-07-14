# BookCrudTask

Sample REST CRUD API with Spring Boot, Mysql, JPA and Hibernate
## Steps to Setup   
1. Clone the application 
   https://github.com/EngAbuBakar/BookCrudTask.git
2. Create Mysql database
## Create database c4
3. Change mysql username and password as per your installation
4. open src/main/resources/application.properties
5. change spring.datasource.username and spring.datasource.password as per your mysql installation

## Build and run the app using maven
The app will start running at http://localhost:8080.
## Explore Rest APIs
The app defines following CRUD APIs.

 ## For user
>GET /users/

>GET /users/{id}

>POST /users/

>PUT /users/{id}

>DELETE /users/{id}

## For Books

>GET /books/

>GET /books/{id}

>POST /books/

>PUT /books/{id}

>DELETE /books/{id}

## For Memberships

>GET /memberships/

>GET /memberships/{id}

>POST /memberships/

>PUT /memberships/{id}

>DELETE /memberships/{id}
