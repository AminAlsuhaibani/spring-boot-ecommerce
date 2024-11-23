
# Spring Boot E-Commerce Project

## Project Description

This is a Spring Boot-based e-commerce application designed to manage products and a shopping cart. It supports basic CRUD operations for products, adding/removing products from the cart, and performing a checkout. The app uses PostgreSQL as the database, Spring Data JPA for persistence, and includes basic tax calculations based on product price.

### Key Features:
- **Add/Edit/View Products**: Supports managing products with details like name, description, price, and tax rate.
- **Shopping Cart**: Allows users to add, remove, and view products in the cart.
- **Checkout**: Calculates the total price, tax, and grand total during checkout, then clears the cart.
- **Product Search**: Search products based on a keyword.
- **API Documentation**: All functionality is exposed through REST APIs.

## Assumptions Made

1. **Tax Calculation**: Products priced above **1000** are taxed at **15%**, and those below **1000** are taxed at **10%**.
2. **Database**: PostgreSQL is used as the database. The `product` table is set with an auto-incrementing `id`.
3. **API Access**: This application is intended for a server-side environment with exposed REST APIs.
4. **Testing**: Basic testing support is included via the `spring-boot-starter-test` dependency for unit and integration tests.

## How to Run the Application

### Prerequisites
- **Java 21** or higher
- **PostgreSQL** installed and running
- **Maven** (for managing dependencies and building the project)

### Steps to Run the Application:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/yourrepository.git
   cd yourrepository
   ```

2. **Set up the Database**:
   - Create a database in PostgreSQL:
     ```sql
     CREATE DATABASE your_database_name;
     ```
   - Make sure the `product` table is set with an `auto-increment` `id` and the `tax_rate` field.
   
3. **Update application properties**:
   Open `src/main/resources/application.properties` and update the database connection settings:
   
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update  # This will automatically create/modify database schema
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

4. **Build the project**:
   If you're using Maven, run the following command to build and run the project:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Access the Application**:
   - The application will be available at `http://localhost:8080`.
   - You can interact with the REST API endpoints using Postman or any API client.

## REST API Details

### 1. GET /api/products
**Description**: Fetches all available products in the database.

**Response**:
```json
[
    {
        "id": 1,
        "name": "Gaming Laptop",
        "price": 5200.0,
        "description": "High-performance laptop",
        "tax_rate": 0.15
    },
    {
        "id": 2,
        "name": "Bluetooth Speaker",
        "price": 599.99,
        "description": "Portable Bluetooth speaker",
        "tax_rate": 0.10
    }
]
```

### 2. POST /api/products
**Description**: Adds a new product to the database.

**Request**:
```json
{
    "name": "New Product",
    "price": 3200.0,
    "description": "A new product description"
}
```

**Response**:
```json
{
    "id": 13,
    "name": "New Product",
    "price": 3200.0,
    "description": "A new product description",
    "tax_rate": 0.15
}
```

### 3. GET /api/products/search?keyword=example
**Description**: Search for products by keyword (in name or description).

**Response**:
```json
[
    {
        "id": 1,
        "name": "Gaming Laptop",
        "price": 5200.0,
        "description": "High-performance laptop",
        "tax_rate": 0.15
    }
]
```

### 4. POST /api/add_to_cart/{productId}
**Description**: Adds a product to the shopping cart by product ID.

**Response**:
```json
{
    "product": {
        "id": 1,
        "name": "Gaming Laptop",
        "price": 5200.0,
        "description": "High-performance laptop",
        "tax_rate": 0.15
    },
    "quantity": 1,
    "totalPrice": 5200.0,
    "totalTax": 780.0
}
```

### 5. DELETE /api/remove_from_cart/{productId}
**Description**: Removes a product from the shopping cart by product ID.

**Response**:
```json
{
    "message": "Product removed from cart"
}
```

### 6. GET /api/view_cart
**Description**: Fetches all products in the cart along with their quantity, total price, and tax.

**Response**:
```json
[
    {
        "product": {
            "id": 1,
            "name": "Gaming Laptop",
            "price": 5200.0,
            "description": "High-performance laptop",
            "tax_rate": 0.15
        },
        "quantity": 1,
        "totalPrice": 5200.0,
        "totalTax": 780.0
    }
]
```

### 7. POST /api/checkout
**Description**: Performs checkout and clears the cart, calculating the total price, total tax, and grand total.

**Response**:
```json
{
    "message": "Checkout successful",
    "products": [
        {
            "name": "Gaming Laptop",
            "price": 5200.0,
            "quantity": 1,
            "subtotal": 5200.0,
            "tax": 780.0
        }
    ],
    "total": 5200.0,
    "totalTax": 780.0,
    "grandTotal": 5980.0
}
```

