SET time_zone = '+09:00';

INSERT INTO users (name, email) VALUES ('Sakura', 'sakura@email.com');
INSERT INTO books (title, status) VALUES ('Ruby on Rails Test Book', 'BORROWED');
INSERT INTO borrow_records (user_id, book_id, borrowed_date) VALUES (1, 1, Now());

INSERT INTO users (name, email) VALUES ('Kenta', 'kenta@email.com');
INSERT INTO books (title, status) VALUES ('Golang Web Server Tutorial', 'BORROWED');
INSERT INTO borrow_records (user_id, book_id, borrowed_date) VALUES (2, 2, Now());

INSERT INTO users (name, email) VALUES ('Yumi', 'yumi@email.com');
INSERT INTO books (title, status) VALUES ('Java Spring Boot Guide', 'BORROWED');
INSERT INTO borrow_records (user_id, book_id, borrowed_date) VALUES (3, 3, Now());

INSERT INTO books (title, status) VALUES ('Java Tutorial', 'BORROWED');
INSERT INTO books (title, status) VALUES ('Master of Java', 'BORROWED');
INSERT INTO books (title) VALUES ('Server Side Architecture');

INSERT INTO
    borrow_records (user_id, book_id, borrowed_date)
VALUES
    (1, 4, Now()),
    (2, 5, Now());
