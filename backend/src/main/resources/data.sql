SET time_zone = '+09:00';

INSERT INTO users
    (name, email, password, role)
VALUES
    ('Master', 'john.doe@example.com', 'pw123', 'ADMIN'),
    ('Jane Smith', 'jane.smith@example.com', 'pw456', 'USER'),
    ('Michael Johnson', 'michael.j@example.com', 'pw789', 'USER'),
    ('David Brown', 'david.brown@example.com', '123pw', 'USER');

INSERT INTO books
    (title, status)
VALUES
    ('The Great Gatsby', 'BORROWED'),
    ('To Kill a Mockingbird', 'BORROWED'),
    ('1984', 'BORROWED'),
    ('Pride and Prejudice', 'BORROWED'),
    ('Moby-Dick', 'AVAILABLE'),
    ('War and Peace', 'AVAILABLE'),
    ('The Catcher in the Rye', 'AVAILABLE'),
    ('Crime and Punishment', 'AVAILABLE');

INSERT INTO borrow_records
    (user_id, book_id, borrowed_date)
VALUES
    (1, 1, Now()),
    (2, 2, Now()),
    (2, 3, Now()),
    (3, 4, Now());
