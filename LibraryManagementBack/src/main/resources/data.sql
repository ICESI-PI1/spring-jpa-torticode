-- Inserts para la tabla Author
INSERT INTO author (name, nationality) VALUES ('Stephen King', 'American');
INSERT INTO author (name, nationality) VALUES ('J.K. Rowling', 'British');
INSERT INTO author (name, nationality) VALUES ('Gabriel García Márquez', 'Colombian');
INSERT INTO author (name, nationality) VALUES ('Danna Espinosa', 'Colombian');
INSERT INTO author (name, nationality) VALUES ('Carlos Bolanyos', 'Spanish');
INSERT INTO author (name, nationality) VALUES ('Victor Bolanyos', 'Spanish');

-- Inserts para la tabla Book
INSERT INTO book (title, publication_date, author_id) VALUES ('The Shining', '1977-01-28', 1);
INSERT INTO book (title, publication_date, author_id) VALUES ('Harry Potter and the Sorcerer''s Stone', '1997-06-26', 2);
INSERT INTO book (title, publication_date, author_id) VALUES ('One Hundred Years of Solitude', '1967-05-30', 3);
INSERT INTO book (title, publication_date, author_id) VALUES ('Metodo de dorfman', '2023-01-01', 5);
INSERT INTO book (title, publication_date, author_id) VALUES ('Miracolous', '2023-02-02', 5);
INSERT INTO book (title, publication_date, author_id) VALUES ('Steven Universe', '2023-03-03', 6);
INSERT INTO book (title, publication_date, author_id) VALUES ('Casos de uso', '2023-04-04', 4);

-- Insert en tabla myusers

INSERT INTO myusers (username, password, rol) VALUES('admin', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'ADMIN');