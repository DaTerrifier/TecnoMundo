# TecnoMundo E-commerce System

TecnoMundo is a collaborative academic e-commerce system with a customer storefront, a REST API, a product administration module, and a shared MySQL database.

The project brings together two independent Java applications: a Java EE storefront for customers and a Spring Boot application for product management.

## Components

| Directory | Component | Technologies | Purpose |
| --- | --- | --- | --- |
| `tecnomundo-web/` | Customer storefront | Java EE, JSP, Servlets, JDBC | Product catalog, customer registration and login, shopping cart, order placement, and order history. |
| `techproducts-api/` | Product API and administration module | Spring Boot, REST API, Spring Data JPA, Hibernate, Thymeleaf | Product management through REST endpoints and a web-based administration interface. |
| `database/` | Shared database schema | MySQL | SQL script to create the database, tables, indexes, and relationships used by both applications. |

## Features

### Customer Storefront

- Browse the technology product catalog.
- Register a customer account, sign in, and sign out.
- Add products to the shopping cart and remove them.
- Place orders and view order history and details.

### Product API and Administration

- Create, retrieve, update, and delete products.
- Search products by ID or name through REST endpoints.
- Manage products through a Thymeleaf administration interface.
- Persist product information using Spring Data JPA and Hibernate.

## Shared Database Integration

Both applications connect to the same `bd_carrito` MySQL database. The storefront uses JDBC, while the product API and administration module use Spring Data JPA.

Product changes made through the administration module are stored in the shared database and become visible when the storefront catalog is refreshed. The applications integrate through this shared schema.

The SQL script in `database/bd_carrito.sql` contains the schema only. It includes no customer, product, or order records.

## REST API Endpoints

The API runs on port `8070` by default.

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/tecnologia/listar` | Retrieve all products |
| GET | `/tecnologia/buscar/{id}` | Retrieve a product by ID |
| GET | `/tecnologia/buscarPorNombre?palabra=...` | Search products by name |
| POST | `/tecnologia/guardar` | Create a new product |
| PUT | `/tecnologia/actualizar/{id}` | Update an existing product |
| DELETE | `/tecnologia/eliminar/{id}` | Delete a product |

### Example Request

Retrieve all products:

```http
GET /tecnologia/listar
```

Example using cURL:

```bash
curl http://localhost:8070/tecnologia/listar
```

## Getting Started

### Prerequisites

- Java 17 or later for the Spring Boot application
- Maven
- MySQL
- A compatible Java EE servlet container for the storefront

### Clone the Repository

```bash
git clone https://github.com/DaTerrifier/TecnoMundo
cd TecnoMundo
```

### Create the Database Schema

Import the schema from the repository root:

```bash
mysql -u your_mysql_user -p < database/bd_carrito.sql
```

The script creates and selects `bd_carrito`, then creates its tables, indexes, and relationships. It does not populate the product catalog.

### Configure the Product API

The Spring Boot application supports environment variables for database configuration.

| Variable | Description |
| --- | --- |
| `DB_URL` | MySQL JDBC connection URL |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |

Example configuration:

```bash
export DB_URL="jdbc:mysql://localhost:3306/bd_carrito"
export DB_USERNAME="your_mysql_user"
export DB_PASSWORD="your_mysql_password"
```

The database schema must already exist. Automatic Hibernate schema updates are disabled.

### Run the Product API and Administration Module

From the repository root, navigate to the Spring Boot application and start it:

```bash
cd techproducts-api
mvn spring-boot:run
```

Product administration interface:

`http://localhost:8070/`

REST API:

`http://localhost:8070/tecnologia/listar`

### Run the Storefront

Deploy `tecnomundo-web/` as a Java EE web application to a compatible servlet container. Its JDBC connection in `tecnomundo-web/src/main/java/config/Conexion.java` must point to the same `bd_carrito` database with valid local connection settings.

The storefront runs separately from the Spring Boot application; its URL depends on the servlet container and deployment context.

## Project Structure

- `README.md`: Documentation for the complete TecnoMundo system.
- `database/`: Shared MySQL schema.
- `tecnomundo-web/`: Java EE customer storefront.
- `techproducts-api/`: Spring Boot REST API and product administration module.

Within `techproducts-api/`, REST controllers expose product endpoints, the MVC controller handles the administration interface, entities and the repository/service interface provide persistence, and Thymeleaf templates render product management pages.

## Academic Context and My Contribution

TecnoMundo was developed collaboratively as a university e-commerce project. The customer storefront was a collaborative effort.

**My primary contribution was the development of the Spring Boot REST API and the product administration module**, including product management endpoints, the Thymeleaf interface, database persistence, and integration with the shared MySQL database.

## Future Improvements

Potential improvements for the API and administration module include:

- Authentication and role-based authorization.
- Request validation.
- Automated API testing.
- Improved exception handling.
- API documentation using OpenAPI/Swagger.

## Disclaimer

This academic project demonstrates e-commerce workflows, backend development, RESTful services, database persistence, and integration between independent applications. It requires additional security, validation, and testing before production use.
