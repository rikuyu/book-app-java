SET time_zone = '+09:00';

INSERT INTO users (name, email) VALUES ('Sakura', 'sakura@email.com');
INSERT INTO books (title) VALUES ('Ruby on Rails Test Book');
INSERT INTO borrow_records (user_id, book_id, borrowed_date) VALUES (1, 1, Now());

INSERT INTO users (name, email) VALUES ('Kenta', 'kenta@email.com');
INSERT INTO books (title) VALUES ('Golang Web Server Tutorial');
INSERT INTO borrow_records (user_id, book_id, borrowed_date) VALUES (2, 2, Now());

INSERT INTO users (name, email) VALUES ('Yumi', 'yumi@email.com');
INSERT INTO books (title) VALUES ('Java Spring Boot Guide');
INSERT INTO borrow_records (user_id, book_id, borrowed_date) VALUES (3, 3, Now());
