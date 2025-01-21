CREATE TABLE IF NOT EXISTS users (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(email)
);

CREATE TABLE IF NOT EXISTS books (
    id INTEGER AUTO_INCREMENT NOT NULL,
    title VARCHAR(50) NOT NULL,
    published_date DATE NOT NULL,
    status ENUM('AVAIlABLE', 'BORROWED') NOT NULL DEFAULT 'AVAIlABLE',
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS borrow_records (
    id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    user_id INTEGER,
    book_id INTEGER,
    borrowed_date DATE NOT NULL,
    returned_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

-- INSERT INTO users (name, email) VALUES ('Sakura', 'sakura@email.com');
-- INSERT INTO books (title, published_date) VALUES ('Ruby on Rails Test Book', '2024-12-06');
-- INSERT INTO borrow_records (user_id, book_id, borrowed_date) VALUES (1, 1, '2024-12-14');

-- INSERT INTO users (name, email) VALUES ('Kenta', 'kenta@email.com');
-- INSERT INTO books (title, published_date) VALUES ('Golang Web Server Tutorial', '2022-02-11');
-- INSERT INTO borrow_records (user_id, book_id, borrowed_date) VALUES (2, 2, '2023-03-09');

-- INSERT INTO users (name, email) VALUES ('Yumi', 'yumi@email.com');
-- INSERT INTO books (title, published_date) VALUES ('Java Spring Boot Guide', '2023-01-20');
-- INSERT INTO borrow_records (id, user_id, book_id, borrowed_date) VALUES (3, 3, '2023-04-15');
