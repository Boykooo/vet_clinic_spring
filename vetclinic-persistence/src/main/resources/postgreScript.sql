DROP SCHEMA IF EXISTS vetclinic CASCADE;

CREATE SCHEMA vetclinic;

CREATE TYPE vetclinic.STATUS AS ENUM ('NEW', 'IN_PROGRESS', 'DONE', 'REJECTED');
CREATE TYPE vetclinic.ROLE AS ENUM ('CLIENT', 'EMPLOYEE', 'ADMIN');

CREATE TABLE vetclinic.client (
  email        VARCHAR(100) PRIMARY KEY,
  password     VARCHAR(100) NOT NULL,
  phone_number VARCHAR(100) NOT NULL,
  first_name   VARCHAR(100) NOT NULL,
  last_name    VARCHAR(100) NOT NULL,
  reg_date     DATE         NOT NULL
);

CREATE TABLE vetclinic.animal (
  id           SERIAL PRIMARY KEY,
  name         VARCHAR(100) NOT NULL,
  age          INTEGER      NOT NULL,
  description  VARCHAR(1000),
  reg_date     DATE         NOT NULL,
  client_email VARCHAR(100) NOT NULL,
  CONSTRAINT fk_client_email FOREIGN KEY (client_email) REFERENCES vetclinic.client (email) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE vetclinic.employee (
  email        VARCHAR(100) PRIMARY KEY,
  password     VARCHAR(100)   NOT NULL,
  phone_number VARCHAR(100)   NOT NULL,
  first_name   VARCHAR(100)   NOT NULL,
  last_name    VARCHAR(100)   NOT NULL,
  reg_date     DATE           NOT NULL,
  role         vetclinic.ROLE NOT NULL
);

CREATE TABLE vetclinic.patient (
  id             SERIAL PRIMARY KEY,
  animal_id      INTEGER          NOT NULL,
  employee_email VARCHAR(100),
  description    VARCHAR(100)     NOT NULL,
  start_date     DATE             NOT NULL,
  end_date       DATE,
  status         vetclinic.STATUS NOT NULL,
  CONSTRAINT fk_animal_id FOREIGN KEY (animal_id) REFERENCES vetclinic.animal (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_employee_email FOREIGN KEY (employee_email) REFERENCES vetclinic.employee (email) ON UPDATE CASCADE
);

-- Basic filling of tables

INSERT INTO vetclinic.employee (email, password, phone_number, first_name, last_name, reg_date, role)
VALUES ('admin@vetclinic.ru', '$2a$04$.QcC5QIw3yM6nRptjgONdOASkXVHyGkFAGgm2vaLuHO0lW8saYZ2W',
        '89205223213', 'Алексей', 'Гаврилов', '2017-07-12', 'ADMIN'
);

INSERT INTO vetclinic.employee (email, password, phone_number, first_name, last_name, reg_date, role)
VALUES ('employee@vetclinic.ru', '$2a$04$vNh3eKOxSg.sFDS1YH7QhOjzS57IYo23D8d68AZfu7URupNkJlXae',
        '89212136010', 'Иван', 'Иванов', '2017-07-12', 'EMPLOYEE'
);

INSERT INTO vetclinic.client (email, password, phone_number, first_name, last_name, reg_date)
VALUES (
  'denis@mail.ru', '$2a$04$dmnXwRIwARmKP.c.QJXyhO0RudkcmNKSE4IifxVb4071zLZZvs.8m',
  '88005553535', 'Денис', 'Валинуров', '2017-07-12'
);

INSERT INTO vetclinic.animal (id, name, age, description, reg_date, client_email)
VALUES (
  1, 'Баксик', 7, 'Описание Баксика', '2017-07-13', 'denis@mail.ru'
);

INSERT INTO vetclinic.animal (id, name, age, description, reg_date, client_email)
VALUES (
  2, 'Алан', 3, 'Описание Алана', '2017-07-14', 'denis@mail.ru'
);

INSERT INTO vetclinic.patient(animal_id, employee_email, description, start_date, end_date, status)
VALUES (
  1, 'employee@vetclinic.ru', 'Описание пациента', '2017-09-24', NULL, 'NEW'
);







