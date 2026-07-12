# 📚 Library Management System

A RESTful Library Management System built using **Spring Boot** that allows users to manage books, issue and return books, and provides secure authentication using **JWT** with **Role-Based Authorization**.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- User Registration
- User Login
- JWT Authentication
- Role-Based Access Control (ADMIN / USER)
- BCrypt Password Encryption

### 📚 Book Management
- Add Book (ADMIN)
- Update Book (ADMIN)
- Delete Book (ADMIN)
- View All Books
- View Book by ID
- Pagination Support

### 📖 Issue Management
- Issue Book
- Return Book
- Update Available Quantity Automatically

### 👥 User Management
- Register New Users
- View Users (ADMIN)

### 🛡 Validation & Exception Handling
- Request Validation
- Global Exception Handling
- Meaningful Error Responses

### 📄 API Documentation
- Swagger UI
- OpenAPI 3 Documentation
- JWT Authorization Support in Swagger

---

# 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT (JSON Web Token)
- Swagger / OpenAPI
- Maven
- Lombok

---

# 📂 Project Structure

```
src
├── config
├── controller
├── dto
├── entity
├── exception
├── filter
├── repository
├── service
└── resources
```

---

# 🔐 Authentication

After login, the API returns:

```json
{
  "message": "Login Successful",
  "username": "Admin12",
  "role": "ADMIN",
  "token": "JWT_TOKEN"
}
```

Use the token for protected APIs.

```
Authorization: Bearer YOUR_JWT_TOKEN
```

---

# 📄 Swagger UI

After running the application:

```
http://localhost:8082/swagger-ui/index.html
```

Authorize using your JWT token to test secured APIs.

---

# 📚 API Endpoints

## Authentication

| Method | Endpoint |
|---------|----------|
| POST | /auth/register |
| POST | /auth/login |

---

## Books

| Method | Endpoint | Access |
|---------|----------|--------|
| GET | /books | USER / ADMIN |
| GET | /books/{id} | USER / ADMIN |
| POST | /books | ADMIN |
| PUT | /books/{id} | ADMIN |
| DELETE | /books/{id} | ADMIN |

---

## Users

| Method | Endpoint | Access |
|---------|----------|--------|
| GET | /users | ADMIN |
| GET | /users/{id} | ADMIN |
| PUT | /users/{id} | ADMIN |
| DELETE | /users/{id} | ADMIN |

---

## Issue Records

| Method | Endpoint |
|---------|----------|
| POST | /issues |
| GET | /issues |
| GET | /issues/{id} |
| PUT | /issues/{issueId}/return |

---

# 📄 Pagination

Retrieve books page-wise.

Example:

```
GET /books?page=0&size=4
```

---

# ▶️ Running the Project

### Clone Repository

```bash
git clone https://github.com/SainathMali/LibraryManagementSpring.git
```

### Open Project

Import as a Maven project in IntelliJ IDEA.

LibraryManagementSystemApplication.java
```

Application starts on:

```
http://localhost:8082
```

---

# 📌 Future Enhancements

- Search Books
- Sorting
- Dashboard Statistics
- Book Categories
- Due Date Management
- Fine Calculation
- Email Notifications

---

# 👨‍💻 Author

**Sainath Mali**

GitHub:
https://github.com/SainathMali

---

⭐ If you like this project, consider giving it a star.