CREATE SCHEMA vetclinic;

CREATE TABLE vetclinic.user (
  email        VARCHAR(100) PRIMARY KEY,
  password     VARCHAR(100) NOT NULL,
  phone_number VARCHAR(100) NOT NULL,
  first_name   VARCHAR(100) NOT NULL,
  last_name    VARCHAR(100) NOT NULL,
  role         VARCHAR(100) NOT NULL
);

CREATE TABLE vetclinic.employee (
  email        VARCHAR(100) PRIMARY KEY,
  password     VARCHAR(100) NOT NULL,
  phone_number VARCHAR(100) NOT NULL,
  first_name   VARCHAR(100) NOT NULL,
  last_name    VARCHAR(100) NOT NULL,
  role         VARCHAR(100) NOT NULL
);


CREATE TABLE vetclinic.patient (
  id             SERIAL PRIMARY KEY,
  animal_name    VARCHAR(100) NOT NULL,
  animal_age     VARCHAR(100) NOT NULL,
  status         VARCHAR(100) NOT NULL,
  user_email     VARCHAR(100) NOT NULL,
  employee_email VARCHAR(100),
  description    VARCHAR(100) NOT NULL,
  start_date     DATE         NOT NULL,
  end_date       DATE,
  CONSTRAINT fk_user_email FOREIGN KEY (user_email) REFERENCES vetclinic.user (email) ON UPDATE CASCADE,
  CONSTRAINT fk_employee_email FOREIGN KEY (employee_email) REFERENCES vetclinic.employee (email) ON UPDATE CASCADE
);