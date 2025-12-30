-- ===============================
-- Library Management System DB
-- ===============================

CREATE DATABASE library_db;
USE library_db;

-- -------------------------------
-- BOOKS TABLE
-- -------------------------------
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    available_copies INT NOT NULL DEFAULT 1
);

-- -------------------------------
-- MEMBERS TABLE
-- -------------------------------
CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(200),
    mobile VARCHAR(20)
);

-- -------------------------------
-- BORROW RECORDS TABLE
-- -------------------------------
CREATE TABLE borrow_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    member_id INT NOT NULL,
    borrow_date DATE NOT NULL,
    return_date DATE,
    returned BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_book
        FOREIGN KEY (book_id)
        REFERENCES books(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_member
        FOREIGN KEY (member_id)
        REFERENCES members(id)
        ON DELETE CASCADE
);

-- -------------------------------
-- SAMPLE DATA (OPTIONAL)
-- -------------------------------

INSERT INTO books (title, author, available_copies) VALUES
('Java Programming', 'James Gosling', 5),
('Clean Code', 'Robert C. Martin', 4),
('Data Structures', 'Mark Allen Weiss', 6);

INSERT INTO members (name, email, mobile) VALUES
('Rahul Singh', 'rahul@gmail.com', '9999999999'),
('Amit Kumar', 'amit@gmail.com', '8888888888'),
('Neha Sharma', 'neha@gmail.com', '7777777777');
