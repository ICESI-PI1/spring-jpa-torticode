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


