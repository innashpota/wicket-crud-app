DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL
);

INSERT INTO users (last_name, first_name, middle_name) VALUES
  ('Korolyuk', 'Volodymyr', 'Semenovych'),
  ('Viazovska', 'Maryna', 'Sergiivna');
