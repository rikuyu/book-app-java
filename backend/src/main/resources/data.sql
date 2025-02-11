SET time_zone = '+09:00';

INSERT INTO users
    (name, email, password, role)
VALUES
    ('Master', 'john.doe@example.com', '$2a$10$FYwnQLWSG.QGelTTW8JD2uJEtncJ9pVOxKaSBg03ddoWx7aWGnpg6', 'ADMIN'),
    ('Jane Smith', 'jane.smith@example.com', '$2a$10$FYwnQLWSG.QGelTTW8JD2uJEtncJ9pVOxKaSBg03ddoWx7aWGnpg6', 'USER'),
    ('Michael Johnson', 'michael.j@example.com', '$2a$10$FYwnQLWSG.QGelTTW8JD2uJEtncJ9pVOxKaSBg03ddoWx7aWGnpg6', 'USER'),
    ('David Brown', 'david.brown@example.com', '$2a$10$FYwnQLWSG.QGelTTW8JD2uJEtncJ9pVOxKaSBg03ddoWx7aWGnpg6', 'USER'),
    ('かがわ たかし', 'kagawa@example.com', '$2a$10$FYwnQLWSG.QGelTTW8JD2uJEtncJ9pVOxKaSBg03ddoWx7aWGnpg6', 'USER'),
    ('いとう あみ', 'itoami@example.com', '$2a$10$FYwnQLWSG.QGelTTW8JD2uJEtncJ9pVOxKaSBg03ddoWx7aWGnpg6', 'USER');

INSERT INTO books
    (title, status)
VALUES
    ('吾輩は猫である', 'BORROWED'),
    ('こころ', 'BORROWED'),
    ('羅生門', 'BORROWED'),
    ('ガリバー旅行記', 'AVAILABLE'),
    ('走れメロス', 'AVAILABLE'),
    ('Java入門', 'AVAILABLE'),
    ('Master of JavaScript', 'AVAILABLE'),
    ('うらしま太郎の物語', 'BORROWED'),
    ('Javaプログラミング完全ガイド', 'BORROWED'),
    ('Effective Java 第3版', 'AVAILABLE'),
    ('JavaとSpring Boot入門', 'AVAILABLE'),
    ('Javaパフォーマンスチューニング', 'AVAILABLE');

INSERT INTO borrow_records
    (user_id, book_id, borrowed_date, returned_date)
VALUES
    (1, 1, '2025-02-01 10:30:00',Now()),
    (2, 1, Now(), NULL),
    (2, 9, '2025-02-22 10:30:00',Now()),
    (3, 9, '2025-03-03 10:30:00',Now()),
    (4, 9, '2025-04-04 10:30:00',Now()),
    (4, 9, Now(), NULL),
    (2, 3, '2025-02-01 10:30:00',Now()),
    (1, 3, Now(), Now()),
    (5, 3, Now(), Now()),
    (6, 3, Now(), NULL),
    (6, 8, Now(), NULL),
    (3, 4, '2025-02-01 10:30:00',Now());
