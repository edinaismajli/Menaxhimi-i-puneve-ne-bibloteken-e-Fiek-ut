INSERT OR IGNORE INTO users (username, password, role)
VALUES
    ('admin', 'admin123', 'ADMIN'),
    ('librarian', 'lib123', 'LIBRARIAN');

INSERT OR IGNORE INTO books (title, author, category, isbn, quantity, available_quantity)
VALUES
    ('Hyrje ne Programim', 'FIEK', 'Programim', '978-100000001', 5, 5),
    ('Struktura te te Dhenave', 'FIEK', 'Programim', '978-100000002', 4, 4),
    ('Rrjeta Kompjuterike', 'FIEK', 'Rrjeta', '978-100000003', 3, 3),
    ('Baza te te Dhenave', 'FIEK', 'Databaza', '978-100000004', 6, 6),
    ('Komunikimi Njeri Kompjuter', 'FIEK', 'KNK', '978-100000005', 2, 2);

INSERT OR IGNORE INTO members (full_name, email, phone, member_type)
VALUES
    ('Arta Krasniqi', 'arta.krasniqi@student.uni-pr.edu', '044111222', 'STUDENT'),
    ('Blend Morina', 'blend.morina@student.uni-pr.edu', '044333444', 'STUDENT'),
    ('Prof. Ilir Berisha', 'ilir.berisha@uni-pr.edu', '044555666', 'STAFF');

INSERT OR IGNORE INTO loans (book_id, member_id, loan_date, due_date, return_date, status)
VALUES
    (1, 1, '2026-05-01', '2026-05-15', NULL, 'ACTIVE'),
    (3, 2, '2026-04-20', '2026-05-04', NULL, 'OVERDUE'),
    (4, 3, '2026-04-10', '2026-04-24', '2026-04-22', 'RETURNED');

INSERT OR IGNORE INTO app_settings (setting_key, setting_value)
VALUES
    ('language', 'sq'),
    ('theme', 'light');
