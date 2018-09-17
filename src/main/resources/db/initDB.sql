DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS typeoffurnitures;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;
DROP SEQUENCE IF EXISTS global_seq;

create sequence global_seq start 100000;

CREATE TABLE departments
(   id                    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name                  VARCHAR not null
);

CREATE TABLE status
(   id                    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name                  VARCHAR not null
);

CREATE TABLE typeoffurnitures
(
  id                                            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name                                          VARCHAR NOT NULL,
  number_of_seconds_performed_by_one_person     INTEGER NOT NULL,
  department__id                                INTEGER,
  FOREIGN KEY (department__id) REFERENCES departments (id) ON DELETE CASCADE
);


CREATE TABLE employees
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name          VARCHAR NOT NULL,
  department_id INTEGER NOT NULL,
  status_work   BOOLEAN NOT NULL,
  FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
  id                   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name                 VARCHAR NOT NULL,
  type_of_furniture_id INTEGER,
  status               VARCHAR,
  create_order         TIMESTAMP,
  end_order            TIMESTAMP,
  actual_deadline      INTEGER,
  FOREIGN KEY (type_of_furniture_id) REFERENCES typeoffurnitures (id) ON DELETE CASCADE
);