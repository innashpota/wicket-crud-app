sudo docker run -p 5432:5432 -e POSTGRES_USER=users ^
-e POSTGRES_PASSWORD=users -e POSTGRES_DB=users ^
--name users_db users:users-db