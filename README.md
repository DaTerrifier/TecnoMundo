# TechProducts Inventory API

A RESTful API and product management application developed with **Java, Spring Boot, Spring Data JPA, and MySQL**.

This project was developed as part of a university e-commerce system. It provides an independent backend service and web-based administration interface for managing technology products.

## Overview

TechProducts Inventory API allows users to create, retrieve, update, delete, and search products through REST endpoints.

The application also includes a product administration interface built with Thymeleaf.

It was designed to integrate with **TecnoMundo**, an existing e-commerce application developed using Java EE, JSP, and Servlets.

Both applications share a MySQL database, allowing changes made through the administration module to be reflected in the TecnoMundo product catalog.

## Technologies

- Java 17
- Spring Boot 3.4.0
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Thymeleaf
- Maven
- REST API

## Features

- RESTful API for product management.
- Create, retrieve, update, and delete products.
- Search products by ID or name.
- Web-based product administration interface.
- Database persistence using Spring Data JPA.
- Integration with an existing e-commerce application through MySQL.

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

## Integration with TecnoMundo

TechProducts and TecnoMundo are independent applications that connect to the same MySQL database.

**TechProducts Inventory API**

- Built with Spring Boot.
- Provides REST endpoints and a product administration interface.
- Uses Spring Data JPA and Hibernate for database persistence.

**TecnoMundo**

- Built with Java EE, JSP, and Servlets.
- Provides the customer-facing e-commerce interface.
- Uses JDBC to retrieve product information from MySQL.

Product changes made through TechProducts are stored in the shared database and become visible in TecnoMundo when its catalog is refreshed.

The integration uses a **shared database**, rather than direct API communication between the applications.

## Getting Started

### Prerequisites

- Java 17 or later
- Maven
- MySQL
- An existing `bd_carrito` database with the required product table

### Database Configuration

The application supports environment variables for database configuration.

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

### Installation

Clone the repository:

```bash
git clone https://github.com/DaTerrifier/techproducts-inventory-api.git
```

Navigate to the project directory:

```bash
cd techproducts-inventory-api
```

Start the application:

```bash
mvn spring-boot:run
```

### Access the Application

Product administration interface:

`http://localhost:8070/`

REST API:

`http://localhost:8070/tecnologia/listar`

## Project Structure

The application is organized into the following components:

- **REST Controller:** Handles HTTP requests and exposes product management endpoints.
- **MVC Controller:** Manages the web-based administration interface.
- **Entity:** Represents product information stored in MySQL.
- **Repository/Service:** Provides database access and product management operations.
- **Thymeleaf Templates:** Render the administration interface.
- **Application Configuration:** Defines database connectivity and Spring Boot settings.

## Academic Context

This application was developed as part of a collaborative university e-commerce project.

My primary contribution was the development of the **Spring Boot REST API and product administration module**, including database persistence and integration with the existing TecnoMundo application.

The TecnoMundo storefront was developed collaboratively.

## Future Improvements

Potential improvements include:

- Authentication and role-based authorization.
- Request validation.
- Automated API testing.
- Improved exception handling.
- API documentation using OpenAPI/Swagger.

## Disclaimer

This is an academic project intended to demonstrate backend development, RESTful services, database persistence, and integration between independent applications.

It is not intended for production use without additional security, validation, and testing.