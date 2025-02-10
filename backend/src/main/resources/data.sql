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
    ('吾輩は猫である', 'BORROWED'),
    ('こころ', 'BORROWED'),
    ('羅生門', 'BORROWED'),
    ('ガリバー旅行記', 'BORROWED'),
    ('走れメロス', 'AVAILABLE'),
    ('Java入門', 'AVAILABLE'),
    ('Master of JavaScript', 'AVAILABLE'),
    ('うらしま太郎の物語', 'AVAILABLE'),
    ('Javaプログラミング完全ガイド', 'AVAILABLE'),
    ('Effective Java 第3版', 'AVAILABLE'),
    ('JavaとSpring Boot入門', 'AVAILABLE'),
    ('Javaパフォーマンスチューニング', 'AVAILABLE');

INSERT INTO borrow_records
    (user_id, book_id, borrowed_date)
VALUES
    (1, 1, Now()),
    (2, 2, Now()),
    (2, 3, Now()),
    (3, 4, Now());
