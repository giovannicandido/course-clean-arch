# Clean Architect Course 

Welcome to `Clean Architect Course`, a wholesome course project demonstrating the principles of Clean Architecture using our
good old Spring Boot!

This project aims to be a foundation to show ideas and professional use cases to inspire new projects on it.

Complementary articles and instructions going to be written upon that. Stay tuned!👌

## What's this all about?

This project will implement simple and complex use cases using the Clean Architecture. Having been developed upon the solid
foundations of Robert C. Martin's Clean Architecture principles, `Clean Architect Course` aims to show the real world
application of these guidelines with Spring Boot.

## Features

- Demonstrates the division of code into domain, application, presentation and infrastructure layers.
- Showcases the Dependency Rule and clean separation of concerns by ensuring that dependencies are only towards the
  center.
- Presents the application of Spring Framework in aligning with Clean Architecture.

## Prerequisite

You're gonna need the following installed on your machine on this magical journey:

- JDK 21
- Docker

### Versions

- Spring Boot 3.2
- Gradle 8.7

## Setting things up

* Run the database with docker compose at the root of the project
* Run the application
* Test it

Swagger URL is http://localhost:8080/swagger-ui.html

## Navigating the project

The project is divided into several layers, each with its own fantastical role:

- **Domain**: Your business objects reside here, holding the fundamental business rules.
- **Application**: These bad boys contain all the specific business rules of the application.
- **Presentation**: This layer contains all the adapters and ports required for the communication of our business rules with
  the outside world.
- **Infrastructure**: The outermost layer with all the frameworks and tools our application needs to run, like databases and
  frameworks.


## The Sacred Texts (aka License)

Copyright 2024 Giovanni Silva

Licensed under the Apache License, Version 2.0

Cheers, and happy coding! 🎉