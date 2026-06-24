# Ecommerce Backend (Spring Boot)

Backend REST API for the simple e-commerce app. Uses in-memory storage (no database).

## Tech Stack

- Java 21
- Spring Boot 3.3.x
- Maven
- spring-dotenv (loads variables from .env)

## Project Structure

\`\`\`
backend/
 ├── src/main/java/com/ecommerce/
 │    ├── EcommerceApplication.java
 │    ├── config/        # CORS configuration
 │    ├── controller/    # REST controllers
 │    ├── service/       # Business logic + in-memory storage
 │    ├── model/         # Domain models (Product, CartItem)
 │    └── dto/           # Request/response objects
 ├── src/main/resources/application.properties
 ├── .env
 ├── .env.example
 ├── .gitignore
 └── pom.xml
\`\`\`

## Environment Variables

Create a \`.env\` file (copy from \`.env.example\`):

\`\`\`
PORT=8080
APP_NAME=EcommerceApp
CORS_ALLOWED_ORIGINS=http://localhost:5173
\`\`\`

These are consumed in \`application.properties\`:

\`\`\`
server.port=\${PORT:8080}
spring.application.name=\${APP_NAME:EcommerceApp}
cors.allowed-origins=\${CORS_ALLOWED_ORIGINS:http://localhost:5173}
\`\`\`

## Run

\`\`\`
mvn clean install
mvn spring-boot:run
\`\`\`

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

\`\`\`
username: admin
password: password
\`\`\`

### Example Requests

Login:
\`\`\`
POST /api/login
{ "username": "admin", "password": "password" }
\`\`\`

Add to cart:
\`\`\`
POST /api/cart/add
{ "productId": 1 }
\`\`\`
