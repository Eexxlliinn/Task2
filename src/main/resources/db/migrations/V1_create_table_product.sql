CREATE TABLE product (
     id SERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     color VARCHAR(50) NOT NULL,
     size VARCHAR(50) NOT NULL,
     price DECIMAL(10, 2) NOT NULL,
     amount INT NOT NULL
);
