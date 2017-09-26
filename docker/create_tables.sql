DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    lastName VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    middleName VARCHAR(255) NOT NULL
);

INSERT INTO users (lastName, firstName, middleName) VALUES
  ('Kovalev', 'A.', 'A.'),
  ('Antonov', 'D.', 'I.'),
  ('Zebrov', 'B.', 'K.');
