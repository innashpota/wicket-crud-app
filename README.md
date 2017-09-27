Users database
=================

##Web application

## How to run

For running ``docker`` command in UNIX / Linux operating system you may need ``sudo``.

Perform the next commands from the root folder of the project step by step:

1. Build docker image for database 
    
    ```
    docker build -t users:users-db -f Dockerfile.db .
    ```

2. Run the database 

    ```
    docker run -p 5432:5432 -e POSTGRES_USER=users -e POSTGRES_PASSWORD=users -e POSTGRES_DB=users --name users_db users:users-db
    ```