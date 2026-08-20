# 📚 Library Management System

A RESTful Library Management System built using **Spring Boot** that allows users to manage books, issue and return books, and provides secure authentication using **JWT** with **Role-Based Authorization**.

---

## 🚀 Features

### 🔐 Authentication & Authorization

* User Registration
* User Login
* JWT Authentication
* Role-Based Access Control (ADMIN / USER)
* BCrypt Password Encryption

### 📚 Book Management

* Add Book (ADMIN)
* Update Book (ADMIN)
* Delete Book (ADMIN)
* View All Books
* View Book by ID
* Pagination Support

### 📖 Issue Management

* Issue Book
* Return Book
* Update Available Quantity Automatically

### 👥 User Management

* Register New Users
* View Users (ADMIN)

### 🛡 Validation & Exception Handling

* Request Validation
* Global Exception Handling
* Meaningful Error Responses

### 📄 API Documentation

* Swagger UI
* OpenAPI 3 Documentation
* JWT Authorization Support in Swagger

### 🐳 Docker Support

* Dockerized Spring Boot Application
* Dockerfile for building the Spring Boot image
* Docker Compose for multi-container setup
* MySQL running in a separate Docker container
* Spring Boot and MySQL connected through a Docker network
* Persistent MySQL data using Docker volumes
* Healthcheck for MySQL
* Easy project setup using Docker Compose

---

# 🛠 Tech Stack

* Java 17
* Spring Boot 3.5.4
* Spring Security
* Spring Data JPA
* Hibernate
* MySQL 8.0
* JWT (JSON Web Token)
* Swagger / OpenAPI
* Maven
* Lombok
* Docker
* Docker Compose

---

# 📂 Project Structure

```text
LibraryManagmentSystem
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.veracity.LibraryManagmentSystem
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── entity
│   │   │       ├── exception
│   │   │       ├── filter
│   │   │       ├── repository
│   │   │       └── service
│   │   │
│   │   └── resources
│   │
│   └── test
│
├── target
├── pom.xml
├── Dockerfile
├── docker-compose.yml
└── README.md
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

```text
Authorization: Bearer YOUR_JWT_TOKEN
```

---

# 📄 Swagger UI

After running the application:

```text
http://localhost:8082/swagger-ui/index.html
```

Authorize using your JWT token to test secured APIs.

---

# 📚 API Endpoints

## Authentication

| Method | Endpoint       |
| ------ | -------------- |
| POST   | /auth/register |
| POST   | /auth/login    |

---

## Books

| Method | Endpoint    | Access       |
| ------ | ----------- | ------------ |
| GET    | /books      | USER / ADMIN |
| GET    | /books/{id} | USER / ADMIN |
| POST   | /books      | ADMIN        |
| PUT    | /books/{id} | ADMIN        |
| DELETE | /books/{id} | ADMIN        |

---

## Users

| Method | Endpoint    | Access |
| ------ | ----------- | ------ |
| GET    | /users      | ADMIN  |
| GET    | /users/{id} | ADMIN  |
| PUT    | /users/{id} | ADMIN  |
| DELETE | /users/{id} | ADMIN  |

---

## Issue Records

| Method | Endpoint                 |
| ------ | ------------------------ |
| POST   | /issues                  |
| GET    | /issues                  |
| GET    | /issues/{id}             |
| PUT    | /issues/{issueId}/return |

---

# 📄 Pagination

Retrieve books page-wise.

Example:

```text
GET /books?page=0&size=4
```

---

# ▶️ Running the Project

## Option 1: Run Locally

### Clone Repository

```bash
git clone https://github.com/SainathMali/LibraryManagementSpring.git
```

### Open Project

Import the project as a Maven project in IntelliJ IDEA.

Run:

```text
LibraryManagmentSystemApplication.java
```

Application starts on:

```text
http://localhost:8082
```

---

# 🐳 Running with Docker

The project supports running the **Spring Boot application and MySQL database together using Docker Compose**.

## Prerequisites

Install:

* Java 17
* Maven
* Docker Desktop

Make sure **Docker Desktop is running** before executing Docker commands.

---

## 1. Clone the Repository

```bash
git clone https://github.com/SainathMali/LibraryManagementSpring.git
```

Go to the project directory:

```bash
cd LibraryManagmentSystem
```

---

## 2. Build the Spring Boot JAR

The Dockerfile copies the generated JAR from the `target` directory.

### Windows

```bash
.\mvnw.cmd clean package
```

### Linux / macOS

```bash
./mvnw clean package
```

After a successful build, the following file should exist:

```text
target/
└── LibraryManagmentSystem-0.0.1-SNAPSHOT.jar
```

---

## 3. Start Docker Compose

Run:

```bash
docker compose up --build -d
```

This command will:

* Build the Spring Boot Docker image
* Pull the MySQL 8.0 image if required
* Create the Spring Boot container
* Create the MySQL container
* Create a Docker network
* Create the MySQL persistent volume
* Start both containers
* Wait for MySQL healthcheck before starting the application

---

## 4. Check Containers

Run:

```bash
docker compose ps
```

You should see two containers:

```text
library-app
library-mysql
```

The MySQL container should show a healthy status.

---

## 5. Access the Application

Spring Boot runs on port `8082`.

Open:

```text
http://localhost:8082
```

Swagger UI:

```text
http://localhost:8082/swagger-ui/index.html
```

---

# 🐳 Docker Architecture

The application uses two containers:

```text
                         Docker Compose
                              │
                ┌─────────────┴─────────────┐
                │                           │
                ▼                           ▼
        ┌───────────────┐           ┌───────────────┐
        │  library-app  │           │ library-mysql │
        │   Spring Boot │           │    MySQL 8.0  │
        │    :8082      │           │     :3306     │
        └───────┬───────┘           └───────┬───────┘
                │                           │
                └──── Docker Network ───────┘
                                            │
                                            ▼
                                     mysql-data
                                       Volume
```

### Spring Boot Container

The Spring Boot application uses the Docker image:

```text
library-management-app
```

The application is exposed to the host machine through:

```text
localhost:8082
```

### MySQL Container

MySQL runs using:

```text
mysql:8.0
```

The MySQL container is named:

```text
library-mysql
```

MySQL port `3306` is used internally by the Docker network.

The MySQL port does not need to be exposed to the host because the Spring Boot container communicates with MySQL internally.

---

# 🌐 Docker Network

Docker Compose automatically creates a network for the services.

Spring Boot connects to MySQL using the Compose service name:

```text
mysql:3306
```

Instead of:

```text
localhost:3306
```

The communication works like:

```text
library-app
     │
     │ mysql:3306
     ▼
library-mysql
```

This allows the two containers to communicate without using the host machine's MySQL server.

---

# 💾 MySQL Persistent Volume

MySQL uses a Docker named volume:

```text
mysql-data
```

The volume is mounted to:

```text
/var/lib/mysql
```

This provides persistent storage for the MySQL database.

```text
MySQL Container
      │
      ▼
/var/lib/mysql
      │
      ▼
mysql-data Volume
      │
      ▼
Persistent Database Data
```

Removing and recreating the container does not automatically remove the named volume.

---

# 🗄️ Database Configuration

When using Docker Compose, the MySQL container creates:

```text
Database: library_db
Username: root
Password: Root@123
```

Spring Boot connects to the Docker MySQL service using:

```text
jdbc:mysql://mysql:3306/library_db
```

The Docker Compose configuration overrides the database connection properties for the containerized application.

---

# 🔧 Useful Docker Commands

### Start containers in background

```bash
docker compose up -d
```

### Build and start containers

```bash
docker compose up --build -d
```

### Check running containers

```bash
docker compose ps
```

### View application logs

```bash
docker compose logs app
```

### View MySQL logs

```bash
docker compose logs mysql
```

### Follow live logs

```bash
docker compose logs -f
```

### Stop and remove containers

```bash
docker compose down
```

### Rebuild after code changes

```bash
.\mvnw.cmd clean package
docker compose up --build -d
```

### List Docker images

```bash
docker images
```

### List Docker volumes

```bash
docker volume ls
```

### List Docker networks

```bash
docker network ls
```

---

# 🔄 Development Workflow

After making changes to the Spring Boot application:

```bash
.\mvnw.cmd clean package
```

Then rebuild the Docker image and restart the containers:

```bash
docker compose up --build -d
```

Check the container status:

```bash
docker compose ps
```

---

# 📌 Future Enhancements

* Search Books
* Sorting
* Dashboard Statistics
* Book Categories
* Due Date Management
* Fine Calculation
* Email Notifications

---

# 👨‍💻 Author

**Sainath Mali**

GitHub:

https://github.com/SainathMali

---

⭐ If you like this project, consider giving it a star.
