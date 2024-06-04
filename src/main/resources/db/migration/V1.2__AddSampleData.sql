-- Insert sample data
INSERT INTO rental_package (code, name) VALUES
('DA', 'Daily'),
('WK', 'Weekly'),
('MO', 'Monthly'),
('YR', 'Yearly');

INSERT INTO car (make, model, year, color, license_plate, price_per_day, price_per_week, price_per_month, price_per_year, availability, keywords) VALUES
  ('Toyota', 'Corolla', 2020, 'White', 'ABC123', 30.00, 200.00, 800.00, 9000.00, TRUE, 'Economy, Compact, Reliable'),
  ('Honda', 'Civic', 2019, 'Black', 'XYZ789', 35.00, 230.00, 900.00, 10000.00, TRUE, 'Sedan, Compact, Comfortable'),
  ('Ford', 'Mustang', 2021, 'Red', 'MUS456', 50.00, 300.00, 1200.00, 15000.00, TRUE, 'Sports, Fast, Stylish'),
  ('BMW', 'X5', 2018, 'Blue', 'BMW123', 70.00, 450.00, 1800.00, 20000.00, TRUE, 'SUV, Luxury, Spacious'),
  ('Chevrolet', 'Malibu', 2022, 'Gray', 'CHE123', 40.00, 250.00, 950.00, 11000.00, TRUE, 'Sedan, Comfortable, Spacious'),
  ('Tesla', 'Model 3', 2023, 'White', 'TES123', 80.00, 500.00, 2000.00, 22000.00, TRUE, 'Electric, Sedan, Advanced'),
  ('Jeep', 'Wrangler', 2021, 'Green', 'JEEP456', 60.00, 360.00, 1440.00, 16000.00, TRUE, 'SUV, Off-road, Adventure'),
  ('Mercedes', 'S-Class', 2020, 'Black', 'MER123', 100.00, 600.00, 2400.00, 26000.00, TRUE, 'Luxury, Sedan, Comfortable');

INSERT INTO reservation (car_id, customer_id, rental_package_code, reservation_date, rental_start_date, rental_end_date) VALUES
  (1, 101, 'DA', '2024-05-01', '2024-05-03', '2024-05-03'),
  (2, 102, 'WK', '2024-05-02', '2024-05-05', '2024-05-12'),
  (3, 103, 'MO', '2024-05-03', '2024-05-07', '2024-06-07'),
  (4, 104, 'YR', '2024-05-04', '2024-05-10', '2025-05-10'),
  (5, 105, 'DA', '2024-05-05', '2024-05-08', '2024-05-08'),
  (6, 106, 'WK', '2024-05-06', '2024-05-10', '2024-05-17'),
  (7, 107, 'MO', '2024-05-07', '2024-05-15', '2024-06-15'),
  (8, 108, 'YR', '2024-05-08', '2024-05-20', '2025-05-20');

INSERT INTO car_rating (car_id, customer_id, score, comment) VALUES
  (4, 104, 5, 'I loved it'),
  (5, 105, 5, 'We loved it'),
  (6, 106, 5, 'She loved it'),
  (7, 107, 5, 'He loved it'),
  (8, 108, 5, 'They loved it'),
  (1, 101, 5, 'I really thought it could have been better'),
  (2, 102, 1, 'I hated it'),
  (3, 103, 1, 'We hated it');


