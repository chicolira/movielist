# ArcTouch Movie List Challenge

This is a simple Angular 7 web app and Spring Boot API project.


## Installation

API:
```bash
mvn clean install -DskipTests
```

Webapp:
```bash
npm install
```

## Run the application

API:
```bash
mvn clean install -DskipTests
mvn spring-boot:run
```

Webapp:
```bash
npm install
ng serve
```

The API documentation can be seen in:
* http://localhost:8080/swagger-ui.html

To access the webapp:
* localhost:4200

## System Architecture
The API was designed to hava a multi-layer architecture:
* Resource Layer: This layer provides the API's REST endpoints
* Service Layer: This layer is responsible for the API's business rules

The WebApp was designed to have a component-based MVC architecture:
* Each component behaves like a MVC
* There was also a service layer to implement the business rules and communicate with the API.

## Dependencies
API:
* Spring Boot Devtools: Provides tools that can improve the developer productivity e.g: hot deploy
* Lombok: Plugs in code in the editor, reducing the amount of code generated e.g: getters and setters
* Springfox Swagger2: Springfox's implementation of Swagger2. 
It simplifies the API documentation.

WebApp:
* ngx-infinite-scroll: Used to implement the infinite scrolling of the movie list page
* ngx-materialize: Angular wrapped materialize library. Materialize is a front end library that implements Google's Material Design specifications.

## License
[Apache](https://apache.org/licenses/LICENSE-2.0)