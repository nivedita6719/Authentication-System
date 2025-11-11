# Spring Boot Authentication System with JWT

A complete authentication system built with Spring Boot, featuring JWT-based authentication, user profile management, and dashboard functionality.

## Features

- ✅ User Registration and Login with JWT
- ✅ JWT Token-based Authentication
- ✅ User Profile Management
- ✅ Dashboard with User Information
- ✅ Secure Password Encryption (BCrypt)
- ✅ CORS Configuration
- ✅ Exception Handling
- ✅ RESTful API Design

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security**
- **Spring Data JPA**
- **JWT (JSON Web Tokens)**
- **H2 Database** (In-Memory for development)
- **Maven**

## Project Structure

```
src/
├── main/
│   ├── java/com/authentication/
│   │   ├── config/
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── ProfileController.java
│   │   │   └── DashboardController.java
│   │   ├── dto/
│   │   │   ├── AuthResponse.java
│   │   │   ├── LoginRequest.java
│   │   │   ├── RegisterRequest.java
│   │   │   ├── UserProfileResponse.java
│   │   │   └── DashboardResponse.java
│   │   ├── entity/
│   │   │   └── User.java
│   │   ├── exception/
│   │   │   └── GlobalExceptionHandler.java
│   │   ├── repository/
│   │   │   └── UserRepository.java
│   │   ├── security/
│   │   │   └── JwtAuthenticationFilter.java
│   │   ├── service/
│   │   │   ├── AuthService.java
│   │   │   ├── CustomUserDetailsService.java
│   │   │   └── UserService.java
│   │   ├── util/
│   │   │   └── JwtUtil.java
│   │   └── AuthenticationSystemApplication.java
│   └── resources/
│       └── application.properties
└── pom.xml
```

## API Endpoints

### Authentication Endpoints

#### 1. Register User
- **POST** `/api/auth/register`
- **Request Body:**
```json
{
  "username": "johndoe",
  "email": "john@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "1234567890"
}
```
- **Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "johndoe",
  "email": "john@example.com",
  "message": "Registration successful"
}
```

#### 2. Login
- **POST** `/api/auth/login`
- **Request Body:**
```json
{
  "username": "johndoe",
  "password": "password123"
}
```
- **Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "johndoe",
  "email": "john@example.com",
  "message": "Login successful"
}
```

#### 3. Logout
- **POST** `/api/auth/logout`
- **Headers:** `Authorization: Bearer <token>`
- **Response:**
```json
{
  "message": "Logout successful"
}
```

### Profile Endpoint

#### Get User Profile
- **GET** `/api/profile`
- **Headers:** `Authorization: Bearer <token>`
- **Response:**
```json
{
  "id": 1,
  "username": "johndoe",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "1234567890",
  "role": "USER",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

### Dashboard Endpoint

#### Get Dashboard Data
- **GET** `/api/dashboard`
- **Headers:** `Authorization: Bearer <token>`
- **Response:**
```json
{
  "welcomeMessage": "Welcome to your dashboard, John!",
  "username": "johndoe",
  "email": "john@example.com",
  "role": "USER",
  "lastLogin": "2024-01-01T10:00:00",
  "status": "Active"
}
```

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Running the Application

1. **Clone/Navigate to the project directory:**
```bash
cd Authenticationssss
```

2. **Build the project:**
```bash
mvn clean install
```

3. **Run the application:**
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### H2 Console Access
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:authdb`
- Username: `sa`
- Password: (leave empty)

## Testing with Postman

### 1. Register a New User

**Request:**
- Method: `POST`
- URL: `http://localhost:8080/api/auth/register`
- Headers: `Content-Type: application/json`
- Body:
```json
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123",
  "firstName": "Test",
  "lastName": "User",
  "phoneNumber": "1234567890"
}
```

**Expected Response:** 201 Created with JWT token

### 2. Login

**Request:**
- Method: `POST`
- URL: `http://localhost:8080/api/auth/login`
- Headers: `Content-Type: application/json`
- Body:
```json
{
  "username": "testuser",
  "password": "password123"
}
```

**Expected Response:** 200 OK with JWT token

### 3. Get Profile (Protected Endpoint)

**Request:**
- Method: `GET`
- URL: `http://localhost:8080/api/profile`
- Headers: 
  - `Content-Type: application/json`
  - `Authorization: Bearer <your-jwt-token>`

**Expected Response:** 200 OK with user profile data

### 4. Get Dashboard (Protected Endpoint)

**Request:**
- Method: `GET`
- URL: `http://localhost:8080/api/dashboard`
- Headers: 
  - `Content-Type: application/json`
  - `Authorization: Bearer <your-jwt-token>`

**Expected Response:** 200 OK with dashboard data

### 5. Logout

**Request:**
- Method: `POST`
- URL: `http://localhost:8080/api/auth/logout`
- Headers: 
  - `Content-Type: application/json`
  - `Authorization: Bearer <your-jwt-token>`

**Expected Response:** 200 OK

## Postman Collection Setup

1. Create a new Postman Collection named "Authentication System"
2. Create environment variables:
   - `base_url`: `http://localhost:8080`
   - `token`: (will be set automatically after login)
3. For protected endpoints, use `{{token}}` in Authorization header

## Git Workflow

### Create and Push Branch

```bash
# Create a new branch
git checkout -b feature/authentication-system

# Add all files
git add .

# Commit changes
git commit -m "Implement authentication system with JWT, profile, and dashboard"

# Push to remote
git push origin feature/authentication-system
```

### Create Pull Request

1. Go to your Git repository (GitHub/GitLab/Bitbucket)
2. Click "New Pull Request"
3. Select your branch: `feature/authentication-system`
4. Add description:
   - Implemented JWT-based authentication
   - Added user registration and login
   - Created profile and dashboard endpoints
   - Configured Spring Security with JWT filter
   - Added exception handling
5. Request review and merge

## Configuration

### JWT Settings
Edit `src/main/resources/application.properties`:
```properties
jwt.secret=your-secret-key-here
jwt.expiration=86400000  # 24 hours in milliseconds
```

### Database
Currently using H2 in-memory database. To switch to MySQL/PostgreSQL:
1. Update `pom.xml` dependencies
2. Update `application.properties` with database connection details

## Security Features

- ✅ Password encryption with BCrypt
- ✅ JWT token-based authentication
- ✅ Stateless session management
- ✅ CORS configuration
- ✅ Role-based access control (ready for extension)
- ✅ Input validation

## Notes

- JWT tokens expire after 24 hours (configurable)
- Passwords are hashed using BCrypt
- H2 database is in-memory (data resets on restart)
- For production, use a persistent database (MySQL/PostgreSQL)

## Troubleshooting

1. **Port 8080 already in use:**
   - Change `server.port` in `application.properties`

2. **JWT token invalid:**
   - Ensure token is included in Authorization header as `Bearer <token>`
   - Check token expiration

3. **CORS errors:**
   - Update `cors.allowed-origins` in `application.properties`

## License

This project is for educational purposes.



