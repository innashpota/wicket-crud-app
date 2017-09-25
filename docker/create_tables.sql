DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    lastName TEXT NOT NULL,
    firstName TEXT NOT NULL,
    middleName TEXT NOT NULL
);

INSERT INTO users (lastName, firstName, middleName) VALUES
  ('Kovalev', 'A.', 'A.'),
  ('Antonov', 'D.', 'I.'),
  ('Zebrov', 'B.', 'K.');
