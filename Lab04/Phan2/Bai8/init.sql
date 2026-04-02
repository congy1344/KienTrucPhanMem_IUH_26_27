-- Tạo database
CREATE DATABASE lab04_db;

-- Kết nối vào database vừa tạo
\c lab04_db;

-- Tạo bảng users
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tạo bảng products
CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(200) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    stock INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Chèn dữ liệu mẫu vào bảng users
INSERT INTO users (name, email) VALUES
    ('Nguyen Van A', 'vana@example.com'),
    ('Tran Thi B',   'thib@example.com'),
    ('Le Van C',     'vanc@example.com');

-- Chèn dữ liệu mẫu vào bảng products
INSERT INTO products (product_name, price, stock) VALUES
    ('San pham A', 150000, 100),
    ('San pham B', 250000, 50),
    ('San pham C', 99000,  200);
