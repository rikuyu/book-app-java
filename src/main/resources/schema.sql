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
    status ENUM('available', 'borrowed') NOT NULL DEFAULT 'available',
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS borrow_records (
    id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    user_id INTEGER,
    book_id INTEGER,
    borrowed_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

-- INSERT INTO users (name, email) VALUES ('Sakura', 'sakura@email.com');
-- INSERT INTO books (title, published_date) VALUES ('Spring Boot Test Book', '2024-12-06');
