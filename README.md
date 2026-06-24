# Java_24J — E-commerce Backend (Spring Boot)

This repository contains the **backend** of a simple full-stack e-commerce app.
The **frontend** (React + Vite) lives in a separate repository: **React_24J**.

## Project Overview

A simple e-commerce demo with a login page, a product catalog, and cart
functionality. No database is used — all data is stored in memory.

- **Backend (this repo):** Java 21, Spring Boot 3.3.x, Maven
- **Frontend (React_24J):** React 18 + Vite
- **Communication:** REST API (JSON)

## Features

- Dummy authentication (POST /api/login) returning a token
- Product listing (GET /api/products)
- Cart: view, add, remove (GET /api/cart, POST /api/cart/add, POST /api/cart/remove)
- CORS enabled and configurable via environment variables
- In-memory storage using List / Map

## Folder Structure

```
backend/
 ├── src/main/java/com/ecommerce/
 │    ├── EcommerceApplication.java
 │    ├── config/        # CORS configuration (WebConfig)
 │    ├── controller/    # AuthController, ProductController, CartController
 │    ├── service/       # AuthService, ProductService, CartService
 │    ├── model/         # Product, CartItem
 │    └── dto/           # LoginRequest, LoginResponse, CartRequest
 ├── src/main/resources/application.properties
 ├── .env
 ├── .env.example
 ├── .gitignore
 ├── README.md
 └── pom.xml
```

## Environment Variables

Create `backend/.env` (copy from `backend/.env.example`):

```
PORT=8080
APP_NAME=EcommerceApp
CORS_ALLOWED_ORIGINS=http://localhost:5173
```

## Run Instructions

```
cd backend
mvn clean install
mvn spring-boot:run
```

Backend runs on http://localhost:8080

## API Endpoints

| Method | Endpoint           | Description                  |
|--------|--------------------|------------------------------|
| POST   | /api/login         | Dummy auth, returns a token  |
| GET    | /api/products      | List all products            |
| GET    | /api/cart          | Get current cart items       |
| POST   | /api/cart/add      | Add a product to the cart    |
| POST   | /api/cart/remove   | Remove a product from cart   |

### Demo Credentials

```
username: admin
password: password
```

## Frontend

The React frontend is in the **React_24J** repository. Run it with:

```
cd frontend
npm install
npm run dev
```

Frontend runs on http://localhost:5173
