# ShopSphere - A Scalable E-commerce Monolith (Phase 1)

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-blueviolet)
![JWT](https://img.shields.io/badge/Security-JWT-purple)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue)

This project is the first phase in the development of a modern e-commerce platform. It is a complete, secure, and functional monolithic application built with Spring Boot. It serves as the foundation for a future decomposition into a resilient, event-driven microservices architecture.

---

## Features

- **Secure User Management**: Full user registration and login functionality with secure, hashed passwords using BCrypt.
- **Stateless Authentication**: End-to-end security implemented with Spring Security 6 and JSON Web Tokens (JWT).
- **Role-Based Authorization**: Differentiated access levels between regular users (`ROLE_USER`) and administrators (`ROLE_ADMIN`).
- **Product & Inventory Management**: Admins can create and manage products along with their real-time stock levels.
- **Transactional Order Processing**: A robust, transactional order placement system that validates user identity and checks product inventory before confirming an order.
- **Centralized Exception Handling**: A clean, global exception handling mechanism provides consistent error responses.
- **RESTful API Design**: A clean and conventional API design for all resources.

---

## Technology Stack

- **Framework**: Spring Boot
- **Language**: Java 17
- **Security**: Spring Security 6, JWT (jjwt library)
- **Database**: PostgreSQL
- **Data Access**: Spring Data JPA
- **Build Tool**: Maven

---

## Getting Started

### Prerequisites

- JDK 17 or higher
- Apache Maven 3.6+
- PostgreSQL server running locally

### Configuration

This project uses environment variables to manage secrets. Before running the application, you must set the following variables.

The `src/main/resources/application.properties` file expects:
- `DB_USERNAME`: Your PostgreSQL username.
- `DB_PASSWORD`: Your PostgreSQL password.
- `JWT_SECRET_KEY`: A long, Base64-encoded secret key for signing JWTs.

**Example for IntelliJ IDEA Run Configuration:**
Go to `Run -> Edit Configurations...` and add the following to the "Environment variables" field:
`DB_USERNAME=your_user;DB_PASSWORD=your_pass;JWT_SECRET_KEY=your_secret_key`

### Running the Application

1.  Clone the repository.
2.  Set up the environment variables as described above.
3.  Create a PostgreSQL database named `shopsphere_db` (or as configured in `application.properties`).
4.  Run the application using your IDE or via the command line:
    ```bash
    mvn spring-boot:run
    ```
The application will be available at `http://localhost:8080`.

---

## API Endpoints

| Method  | Endpoint                         | Protection   | Description                                           |
|---------|----------------------------------|--------------|-------------------------------------------------------|
| `POST`  | `/api/v1/users`                  | **Public** | Register a new user (defaults to `ROLE_USER`).          |
| `POST`  | `/api/v1/auth/login`             | **Public** | Log in to receive a JWT.                                |
| `GET`   | `/api/v1/products`               | Authenticated | Get a list of all available products.                |
| `POST`  | `/api/v1/products`               | **ADMIN** | Add a new product to the catalog.                        |
| `POST`  | `/api/v1/orders`                 | Authenticated | Place a new order for one or more products.          |
