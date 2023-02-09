# RBC stoke.repo

This is a sample Spring Boot application that demonstrates how to build and run a simple web-based stock application using the Spring framework.

## Requirements
Before you can run this application, you'll need to have the following installed on your system:

JDK 8 or later
Maven 2
MongoDB
Docker (optional)

## Running the application locally
To run a Spring Boot application on your local machine, execute the main method in the com.challenge.rbc.RbcApplication class from your IDE.
Alternatively you can use the Spring Boot Maven plugin. To run the application using Maven, simply navigate to the root directory of the project and run the following command:

mvn spring-boot:run

This will start the application.

Using Docker: If you have Docker installed, you can also run the application in a Docker container. To do this, navigate to the root directory of the project and run the following command:

docker-compose up

This will build the Docker image and start the container, and make the application available at `http://localhost:8080`.

## Accessing the Application

Once the application is running, you can use Postman to hit the url for different methods, like:

bulk insert: http://localhost:8080/api/stock-data/bulk-insert
add: http://localhost:8080/api/stock-data/add

