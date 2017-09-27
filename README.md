Users database
=================

## Web application

Simple CRUD application written with Spring boot, Apache Wicket, Spring security, JDBC and PostgreSQL.

## Environment Setup

- JDK 1.8 or above;
- Maven 4.0.0 or above;
- Docker 17.05.0.


## How to run

1. Build docker image for database [build-db-image.sh](./build-db-image.sh).

2. Run the database [start-db.sh](./start-db.sh).
    
3. Build jar-archive with the application [build-application.sh](./build-application.sh).

4. Run the application [start-application.sh](./start-application.sh).