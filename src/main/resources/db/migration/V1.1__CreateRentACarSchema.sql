CREATE TABLE car (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  make VARCHAR(50) NOT NULL,
  model VARCHAR(50) NOT NULL,
  year INT NOT NULL,
  color VARCHAR(20) NOT NULL,
  license_plate VARCHAR(20) NOT NULL UNIQUE,
  price_per_day DECIMAL(10, 2) NOT NULL,
  price_per_week DECIMAL(10, 2) NOT NULL,
  price_per_month DECIMAL(10, 2) NOT NULL,
  price_per_year DECIMAL(10, 2) NOT NULL,
  availability BOOLEAN NOT NULL DEFAULT TRUE,
  keywords VARCHAR(200)
);

CREATE TABLE rental_package (
  code CHAR(2) NOT NULL UNIQUE,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (code)
);

CREATE TABLE reservation (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  car_id BIGINT NOT NULL,
  customer_id BIGINT NOT NULL,
  rental_package_code CHAR(2) NOT NULL,
  reservation_date DATE NOT NULL,
  rental_start_date DATE NOT NULL,
  rental_end_date DATE NOT NULL,
  FOREIGN KEY (car_id) REFERENCES car(id),
  FOREIGN KEY (rental_package_code) REFERENCES rental_package(code)
);

CREATE TABLE car_rating (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  car_id BIGINT,
  customer_id BIGINT,
  score INT,
  comment VARCHAR(100),
  FOREIGN KEY (car_id) REFERENCES car(id)
);
