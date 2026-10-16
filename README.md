# # Expense Tracker API

A RESTful backend for tracking personal expenses, built with **Spring Boot** and **PostgreSQL**. The project focuses on clean structure: a layered architecture, DTOs to keep the database model separate from the API, input validation, and one place that handles all errors.

## Features

- Create, read, update and delete expenses through REST endpoints
- Layered architecture: Controller, Service and Repository
- DTO pattern so the API never exposes the database entity directly
- Input validation with clear error responses
- Global exception handling (for example, a `404` when an expense id does not exist)
- Data stored in PostgreSQL using Spring Data JPA and Hibernate

## Tech stack

| Area | Technology |
| --- | --- |
| Language | Java (JDK 17 or newer) |
| Framework | Spring Boot, Spring Web MVC |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Build tool | Maven |
| API testing | Postman |

## How a request flows

```
Client  ->  Controller  ->  Service  ->  Repository  ->  PostgreSQL
(Postman)   (REST + DTO)    (logic)      (JPA)            (expense table)
```

- **Controller** receives the HTTP request and returns the response.
- **Service** holds the business logic and works between DTOs and entities.
- **Repository** talks to the database through Spring Data JPA.
- **GlobalExceptionHandler** turns exceptions into clean error responses.

## Project structure

```
src/main/java/com/expense/tracker
├── ExpenseTrackerApplication.java
├── controller/   ExpenseController
├── dto/          ExpenseDTO
├── entity/       Expense
├── exception/    GlobalExceptionHandler, ResourceNotFoundException
├── repository/   ExpenseRepository
└── service/      ExpenseService
```

## Getting started

### Prerequisites

- JDK 17 or newer
- Maven
- PostgreSQL (the default port is `5432`)

### 1. Create the database

```sql
CREATE DATABASE expense_db;
```

### 2. Configure the connection

Open `src/main/resources/application.properties` and set your own values:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/expense_db
spring.datasource.username=your_username
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

Set the password as an environment variable so it is never stored in the code:

```bash
export DB_PASSWORD=your_password
```

### 3. Run the application

```bash
mvn spring-boot:run
```

The API starts at `http://localhost:8080`. Hibernate creates the `expense` table automatically on the first run.

## API endpoints

Base URL: `http://localhost:8080`

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/expenses` | Create a new expense |
| GET | `/expenses` | Get all expenses |
| GET | `/expenses/{id}` | Get one expense by id |
| PUT | `/expenses/{id}` | Update an expense |
| DELETE | `/expenses/{id}` | Delete an expense |

### Example: create an expense

Request:

```http
POST /expenses
Content-Type: application/json

{
  "title": "Lunch",
  "amount": 250,
  "category": "Food"
}
```

Response:

```json
{
  "id": 1,
  "title": "Lunch",
  "amount": 250.0,
  "category": "Food"
}
```

If an id does not exist (for example `GET /expenses/999`), the API returns a `404 Not Found` error response from the global exception handler.

## Database table

| Column | Type | Notes |
| --- | --- | --- |
| id | bigint | Primary key, generated automatically |
| title | varchar(255) | Required |
| amount | double | Required |
| category | varchar(255) | Optional |

## What I practised

- Designing a layered Spring Boot application
- Separating entities from DTOs
- Validating input and handling exceptions in one place
- Working with PostgreSQL through JPA and Hibernate
- Testing REST APIs with Postman

## Planned improvements

- [ ] Swagger / OpenAPI documentation
- [ ] Use `BigDecimal` for amounts instead of `double`
- [ ] Unit tests with JUnit and Mockito
- [ ] Filtering by category and date, plus totals
- [ ] React frontend
- [ ] Authentication with JWT

## Author

**Adity Kumari Gupta** - Full Stack Java Developer in the making.
[GitHub](https://github.com/Adity0517)