# 📚 Library Management System (Java Swing + MySQL)

A **desktop-based Library Management System** developed using **Java Swing** for the user interface and **MySQL** for persistent data storage.  
The application supports **complete CRUD operations**, **borrowing & returning books**, **searching**, **filtering**, and **sorting** with a clean modular architecture.

---

## 🧾 Project Overview

This project is designed to manage library operations efficiently.  
It allows librarians to:

- Manage books and members  
- Issue and return books  
- Track borrowing history  
- Search, filter, and sort records  
- Maintain data integrity using MySQL  

The project follows **good software engineering practices**:

- Separation of concerns (UI, DAO, Model, Utility)
- JDBC-based database integration
- Input validation and error handling
- Responsive UI using SwingWorker

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

# 📚 Library Management System (Java Swing + MySQL)

A **desktop-based Library Management System** developed using **Java Swing** for the user interface and **MySQL** for persistent data storage.  
The application supports **complete CRUD operations**, **borrowing & returning books**, **searching**, **filtering**, and **sorting** with a clean modular architecture.

---

## 🧾 Project Overview

This project is designed to manage library operations efficiently.  
It allows librarians to:

- Manage books and members  
- Issue and return books  
- Track borrowing history  
- Search, filter, and sort records  
- Maintain data integrity using MySQL  

The project follows **good software engineering practices**:

- Separation of concerns (UI, DAO, Model, Utility)
- JDBC-based database integration
- Input validation and error handling
- Responsive UI using SwingWorker

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
├── src/
│ └── com/library/
│ ├── app/
│ │ └── LibraryApp.java
│ ├── dao/
│ │ ├── BookDAO.java
│ │ ├── MemberDAO.java
│ │ └── BorrowDAO.java
│ ├── model/
│ │ ├── Book.java
│ │ ├── Member.java
│ │ └── BorrowRecord.java
│ └── util/
│ ├── DBUtil.java
│ └── ValidationUtil.java
├── resources/
│ └── schema.sql
├── lib/
│ └── mysql-connector-j-8.x.x.jar
├── README.md


---

## ⚙️ Prerequisites

Before running the project, ensure the following are installed:

### 1. Java JDK 17 or higher

java -version

### 2. MySQL Server (8.x recommended)
Ensure MySQL service is running.

### 3. VS Code
- Java Extension Pack installed

### 4. MySQL Connector/J
Download from:  
https://dev.mysql.com/downloads/connector/j/  

Place the `.jar` file inside the `lib/` folder.

---

## 🗄️ Database Setup

### Step 1: Open MySQL

mysql -u root -p

### Step 2: Create Database

CREATE DATABASE library_db;
USE library_db;

### Step 3: Create Tables
Run `resources/schema.sql`

Example:
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
returned BOOLEAN DEFAULT FALSE
);
---

## 🔧 Database Configuration

Edit `DBUtil.java`:

jdbc:mysql://localhost:3306/library_db?useSSL=false&serverTimezone=UTC

Set your MySQL username and password.

---

## ▶️ How to Run the Project (VS Code)

### Step 1: Open Project Folder

File → Open Folder → LibraryManagementSystem

### Step 2: Add MySQL Connector to Classpath
- Right-click project
- Add Library
- Select mysql-connector-j-8.x.x.jar

### Step 3: Run the Application
Run the main class:

com.library.app.LibraryApp
Or click **Run ▶️** in VS Code.

---

## 🖥️ Application Features

### 📘 Book Management
- Add, edit, delete books
- View available copies
- Search and filter books

### 👤 Member Management
- Add, update, delete members
- Search members

### 🔄 Borrow & Return
- Issue books to members
- Return issued books
- Prevent duplicate returns
- Automatic availability updates

### 🔍 Search, Filter & Sort
- Live text filtering
- Column sorting via table headers
- Filtering without losing sort state

---

## 🧪 Validation & Error Handling

- Mandatory field validation
- Email format validation
- Prevents negative copies
- Graceful SQL exception handling
- User-friendly dialogs

---

## 🚀 Future Enhancements

- Login and authentication
- Role-based access control
- Export reports (PDF / Excel)
- Fine calculation
- Dashboard analytics

---

## 👨‍💻 Author

**Rahul Singh**  
Computer Science Student  
GitHub: https://github.com/rahulsanjaysingh

---

## 📜 License

This project is created for **educational purposes**.  
You are free to modify and enhance it.

---

⭐ If you found this project useful, give it a star on GitHub!
