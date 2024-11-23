
# Spring Boot E-Commerce Project

## Project Description

This is a Spring Boot-based e-commerce application that allows managing products and a shopping cart. It supports basic operations like adding, viewing, and removing products from the cart, along with calculating total prices and taxes during checkout.


### Key Features:
- **Manage Products**: Add, view, and search for products.
- **Shopping Cart**: Add and remove products from the cart.
- **Checkout**: Calculate total price, tax, and grand total during checkout.

## How to Run the Application

### Prerequisites:
- **Java 21** or higher
- **PostgreSQL** installed and running
- **Maven** (for building the project)

### Steps to Run the Application:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/yourrepository.git
   cd yourrepository
   ```

2. **Configure Database**:
   - Create a database in PostgreSQL:
   The database will be automatically created and initialized by the application.
   Ensure PostgreSQL is running on `localhost:5432`

   
3. **Update application properties**:
   Open `src/main/resources/application.properties` and update the database connection settings:
   
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.sql.init.mode=always
   spring.jpa.show-sql=true
   spring.jackson.serialization.indent-output=true
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
     
6. **Import Postman Tests**:
   #### 1. Download the Postman Collection: Download the Postman collection file `shop-api-testing.json` from the repository or from a shared location.

   #### 2. Import into Postman:
   - Open Postman.
   - Click Import in the top-left corner.
   - Select Upload Files and choose the `shop-api-testing.json` file.
   - Click Import.
     
   #### 3. Download the Postman Collection: Download the Postman collection file shop-api-testing.json from the repository or from a shared location.

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

