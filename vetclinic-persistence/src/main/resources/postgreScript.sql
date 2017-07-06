DROP SCHEMA vetclinic CASCADE;

CREATE SCHEMA vetclinic;

CREATE TYPE vetclinic.STATUS AS ENUM ('new', 'in_progress', 'done', 'rejected');
CREATE TYPE vetclinic.ROLE AS ENUM ('USER', 'EMPLOYEE', 'ADMIN');

CREATE TABLE vetclinic.user (
  email        VARCHAR(100) PRIMARY KEY,
  password     VARCHAR(100) NOT NULL,
  phone_number VARCHAR(100) NOT NULL,
  first_name   VARCHAR(100) NOT NULL,
  last_name    VARCHAR(100) NOT NULL,
  reg_date     DATE         NOT NULL
);

CREATE TABLE vetclinic.animal (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(100) NOT NULL,
  age         INTEGER      NOT NULL,
  description VARCHAR(1000),
  reg_date    DATE         NOT NULL,
  user_email  VARCHAR(100) NOT NULL,
  CONSTRAINT fk_user_email FOREIGN KEY (user_email) REFERENCES vetclinic.user (email) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE vetclinic.employee (
  email        VARCHAR(100) PRIMARY KEY,
  password     VARCHAR(100) NOT NULL,
  phone_number VARCHAR(100) NOT NULL,
  first_name   VARCHAR(100) NOT NULL,
  last_name    VARCHAR(100) NOT NULL,
  reg_date     DATE         NOT NULL,
  role         ROLE         NOT NULL
);

CREATE TABLE vetclinic.patient (
  id             SERIAL PRIMARY KEY,
  status         VARCHAR(100) NOT NULL,
  animal_id      INTEGER      NOT NULL,
  employee_email VARCHAR(100),
  description    VARCHAR(100) NOT NULL,
  start_date     DATE         NOT NULL,
  end_date       DATE,
  curr_status    STATUS       NOT NULL,
  CONSTRAINT fk_animal_id FOREIGN KEY (animal_id) REFERENCES vetclinic.animal (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_employee_email FOREIGN KEY (employee_email) REFERENCES vetclinic.employee (email) ON UPDATE CASCADE
);


