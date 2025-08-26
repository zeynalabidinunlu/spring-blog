# Blog Platform API

A full-featured blog platform built with Spring Boot that provides RESTful APIs for content management, user authentication, and blog organization.

## Features

### Core Functionality
- **User Authentication** - JWT-based authentication system
- **Post Management** - Create, read, update, and delete blog posts
- **Category System** - Organize posts into hierarchical categories
- **Tag System** - Flexible tagging for content classification
- **Draft System** - Save posts as drafts before publishing
- **Reading Time Calculation** - Automatic estimation of reading time

### Security
- JWT token-based authentication
- Stateless session management
- Protected endpoints for content management
- Public access for published content

## Technology Stack

- **Backend Framework**: Spring Boot 3.4.0
- **Database**: PostgreSQL (production), H2 (testing)
- **Authentication**: Spring Security + JWT
- **ORM**: Spring Data JPA with Hibernate
- **Mapping**: MapStruct
- **Build Tool**: Maven
- **Java Version**: 21
- **Containerization**: Docker Compose

## Prerequisites

- Java 21 or later
- Docker and Docker Compose
- Node.js 20+ (for frontend)
- Maven (bundled with project)

## Getting Started

### 1. Clone and Setup
```bash
git clone <your-repository-url>
cd blog-platform
```

### 2. Database Setup
Start PostgreSQL using Docker Compose:
```bash
docker-compose up -d
```

Access database management interface at http://localhost:8888 with:
- System: PostgreSQL
- Server: db
- Username: postgres
- Password: changemeinprod!

### 3. Configure Application
Update `src/main/resources/application.properties`:
```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=changemeinprod!

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=your-256-bit-secret-key-here-make-it-at-least-32-bytes-long
```

### 4. Build and Run
```bash
./mvnw clean install
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

### 5. Frontend Setup
If using the provided React frontend:
```bash
cd frontend
npm install
npm run dev
```
Frontend will be available at `http://localhost:5173`

## API Documentation

### Authentication

#### Login
```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "email": "user@test.com",
  "password": "password"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "expiresIn": 86400
}
```

### Categories

#### List Categories
```http
GET /api/v1/categories
```

#### Create Category
```http
POST /api/v1/categories
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Technology"
}
```

#### Delete Category
```http
DELETE /api/v1/categories/{id}
Authorization: Bearer <token>
```

### Tags

#### List Tags
```http
GET /api/v1/tags
```

#### Create Tags
```http
POST /api/v1/tags
Authorization: Bearer <token>
Content-Type: application/json

{
  "names": ["spring-boot", "java", "tutorial"]
}
```

#### Delete Tag
```http
DELETE /api/v1/tags/{id}
Authorization: Bearer <token>
```

### Posts

#### List Published Posts
```http
GET /api/v1/posts
GET /api/v1/posts?categoryId={uuid}
GET /api/v1/posts?tagId={uuid}
GET /api/v1/posts?categoryId={uuid}&tagId={uuid}
```

#### Get Single Post
```http
GET /api/v1/posts/{id}
```

#### List Draft Posts (Authenticated)
```http
GET /api/v1/posts/drafts
Authorization: Bearer <token>
```

#### Create Post
```http
POST /api/v1/posts
Authorization: Bearer <token>
Content-Type: application/json

{
  "title": "My Blog Post",
  "content": "This is the content of my blog post...",
  "categoryId": "uuid",
  "tagIds": ["uuid1", "uuid2"],
  "status": "PUBLISHED"
}
```

#### Update Post
```http
PUT /api/v1/posts/{id}
Authorization: Bearer <token>
Content-Type: application/json

{
  "id": "uuid",
  "title": "Updated Title",
  "content": "Updated content...",
  "categoryId": "uuid",
  "tagIds": ["uuid1", "uuid2"],
  "status": "DRAFT"
}
```

#### Delete Post
```http
DELETE /api/v1/posts/{id}
Authorization: Bearer <token>
```

## Data Models

### Post Status
- `DRAFT` - Post saved but not published
- `PUBLISHED` - Post visible to public

### Entities
- **User** - Blog authors with authentication
- **Post** - Blog content with title, content, status
- **Category** - Hierarchical content organization
- **Tag** - Flexible content classification

## Testing

### Run Tests
```bash
./mvnw test
```

Tests use H2 in-memory database for isolation and speed.

### Test Configuration
Test-specific configuration in `src/test/resources/application.properties`:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
```

## Error Handling

The API returns standardized error responses:

```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": [
    {
      "field": "title",
      "message": "Title is required"
    }
  ]
}
```

Common HTTP status codes:
- `200` - Success
- `201` - Created
- `204` - No Content (for deletions)
- `400` - Bad Request
- `401` - Unauthorized
- `404` - Not Found
- `409` - Conflict

## Default User

The application creates a default user for testing:
- **Email**: user@test.com
- **Password**: password

## Project Structure

```
src/main/java/com/devtiro/blog/
├── controllers/         # REST API endpoints
├── services/           # Business logic
├── repositories/       # Data access layer
├── domain/
│   ├── entities/       # JPA entities
│   └── dtos/          # Data Transfer Objects
├── mappers/           # MapStruct mappers
├── config/            # Configuration classes
└── security/          # Authentication components
```

## License

This project is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.

## Attribution

"Build a Blog Platform App by Devtiro is licensed under CC BY-NC-SA 4.0. Original content available at https://www.youtube.com/@devtiro and https://www.devtiro.com."

## Development Notes

- Uses UUID primary keys for better security
- Implements lazy loading for performance
- Includes automatic timestamp management
- Validates all inputs with Bean Validation
- Supports filtering published posts only for public access
- Implements proper cascade operations for data integrity
