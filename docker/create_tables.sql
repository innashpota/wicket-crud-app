DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    lastName VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    middleName VARCHAR(255) NOT NULL
);

INSERT INTO users (lastName, firstName, middleName) VALUES
  ('Korolyuk', 'Volodymyr', 'Semenovych'),
  ('Viazovska', 'Maryna', 'Sergiivna'),
  ('Paton', 'Yevgen', 'Oskarovich'),
  ('Drinfeld', 'Volodymyr', 'Gershonovich'),
  ('Tymoshenko', 'Stepan', 'Prokopovych'),
  ('Paton', 'Borys', 'Yevhenovych'),
  ('Horbulin', 'Volodymyr', 'Pavlovych');
