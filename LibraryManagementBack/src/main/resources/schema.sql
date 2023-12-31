-- Crear la tabla Author
CREATE TABLE IF NOT EXISTS author (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    nationality VARCHAR(255)
);

-- Crear la tabla Book
CREATE TABLE IF NOT EXISTS book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    publication_date DATE,
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES author (id)
);

CREATE TABLE IF NOT EXISTS myusers (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(255) CHECK (rol IN ('ADMIN', 'USER')) NOT NULL
);


