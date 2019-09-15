DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS addresses;

CREATE TABLE addresses (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  street VARCHAR(250) DEFAULT NULL,
  city VARCHAR(250) DEFAULT NULL,
  state VARCHAR(250) DEFAULT NULL,
  zip VARCHAR(250) DEFAULT NULL
);

CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  address_id INT DEFAULT NULL,
  FOREIGN KEY (address_id) REFERENCES addresses(id)
);

INSERT INTO addresses (street, city, state, zip) VALUES
('Virginia Rd', 'White Plains', 'NY', '10603');
INSERT INTO users (first_name, last_name, email, address_id) VALUES
  ('Raja', 'Khan', 'getrk@yahoo.com', LAST_INSERT_ID());

INSERT INTO addresses (street, city, state, zip) VALUES
('512 Azalea Dr', 'N Brunswick', 'NJ', '08902');
INSERT INTO users (first_name, last_name, email, address_id) VALUES
  ('Zidan', 'Khan', 'getrk2@gmail.com', LAST_INSERT_ID());

INSERT INTO addresses (street, city, state, zip) VALUES
('512 Azalea Dr', 'N Brunswick', 'NJ', '08902');
INSERT INTO users (first_name, last_name, email, address_id) VALUES
  ('John', 'Smith', 'someemail@gmail.com', LAST_INSERT_ID());