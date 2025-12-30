# LibraryManagementSystem
# 📚 Library Management System (Java Swing + MySQL)

A **desktop-based Library Management System** developed using **Java Swing** for the user interface and **MySQL** for persistent data storage.  
The application supports **complete CRUD operations**, **borrowing & returning books**, **searching**, **filtering**, and **sorting**, with a clean modular architecture.

---

## 🧾 Project Overview

This project is designed to manage library operations efficiently.  
It allows librarians to:

- Manage books and members
- Issue and return books
- Track borrowing history
- Search, filter, and sort records
- Maintain data integrity using MySQL

The project follows **good software engineering practices**, including:
- Separation of concerns (UI, DAO, Model, Utility)
- JDBC-based database integration
- Input validation and error handling
- Responsive UI using `SwingWorker` (multi-threading)

---

## 🛠️ Technologies Used

| Technology | Purpose |
|----------|--------|
| Java (JDK 17+) | Core programming language |
| Java Swing | Desktop GUI |
| MySQL | Relational database |
| JDBC | Database connectivity |
| VS Code | Development environment |
| Git & GitHub | Version control |

---

## 📁 Project Structure

LibraryManagementSystem/
│
├── src/
│ └── com/library/
│ ├── app/
│ │ └── LibraryApp.java # Main UI with Tabs
│ │
│ ├── dao/
│ │ ├── BookDAO.java # Book CRUD logic
│ │ ├── MemberDAO.java # Member CRUD logic
│ │ └── BorrowDAO.java # Issue/Return logic
│ │
│ ├── model/
│ │ ├── Book.java # Book entity
│ │ ├── Member.java # Member entity
│ │ └── BorrowRecord.java # Borrow entity
│ │
│ └── util/
│ ├── DBUtil.java # Database connection utility
│ └── ValidationUtil.java # Input validation helpers
│
├── resources/
│ └── schema.sql # Database schema
│
├── lib/
│ └── mysql-connector-j-8.x.x.jar # MySQL JDBC driver
│
├── README.md
└── .gitignore

yaml
Copy code

---

## ⚙️ Prerequisites

Before running the project, ensure the following are installed:

1. **Java JDK 17 or higher**
   ```bash
   java -version
MySQL Server (8.x recommended)
Ensure MySQL service is running.

VS Code

Java Extension Pack installed

MySQL Connector/J

Download from: https://dev.mysql.com/downloads/connector/j/

Place the .jar file inside the lib/ folder

🗄️ Database Setup (IMPORTANT)
Step 1: Open MySQL
Login using terminal or MySQL Workbench:

sql
Copy code
mysql -u root -p
Step 2: Create Database
sql
Copy code
CREATE DATABASE library_db;
USE library_db;
Step 3: Create Tables
Run the SQL present in resources/schema.sql.

Example schema:

sql
Copy code
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    isbn VARCHAR(50),
    total_copies INT NOT NULL,
    available_copies INT NOT NULL
);

CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(200),
    mobile VARCHAR(50)
);

CREATE TABLE borrow_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    member_id INT,
    borrow_date DATE,
    return_date DATE,
    returned BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (member_id) REFERENCES members(id)
);
🔧 Database Configuration
Edit DBUtil.java according to your MySQL credentials:

java
Copy code
private static final String URL =
    "jdbc:mysql://localhost:3306/library_db?useSSL=false&serverTimezone=UTC";
private static final String USER = "root";
private static final String PASSWORD = "your_mysql_password";
▶️ How to Run the Project (VS Code)
Step 1: Open Project Folder
mathematica
Copy code
File → Open Folder → LibraryManagementSystem
Step 2: Add MySQL Connector to Classpath
Right-click project → Add Library

Select mysql-connector-j-8.x.x.jar

Step 3: Run the Application
Run the main class:

java
Copy code
com.library.app.LibraryApp
Or simply click Run ▶️ in VS Code.

🖥️ Application Features
📘 Book Management
Add new books

Edit book details

Delete books

View availability

Search & filter books

👤 Member Management
Add members

Update member details

Delete members

Search members

🔄 Borrow & Return
Issue book to member

Return issued books

Prevent duplicate returns

Automatic availability updates

🔍 Search, Filter & Sort
Live text-based filtering

Column sorting via JTable headers

Filtering without losing sorting state

⚡ Performance
Uses SwingWorker for database operations

UI remains responsive during long queries

🧪 Validation & Error Handling
Mandatory field validation

Email format validation

Prevents negative copy counts

Graceful SQL exception handling

User-friendly error dialogs

🧑‍🎓 Evaluation Focus Areas
✔ Core Feature Implementation
✔ Data Validation
✔ Error Handling & Robustness
✔ Component Integration
✔ Code Quality & Modularity
✔ Innovation & UI Usability
✔ Proper Documentation

🚀 Future Enhancements
Login & authentication

Role-based access control

Export reports (PDF / Excel)

Due-date & fine calculation

Dashboard analytics

👨‍💻 Author
Rahul Singh
Computer Science Student
GitHub: https://github.com/rahulsanjaysingh

📜 License
This project is created for educational purposes.
You are free to modify and enhance it.