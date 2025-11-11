# Quick Start Guide

## Step 1: Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Step 2: Test with Postman

### Option A: Import Postman Collection
1. Open Postman
2. Click "Import"
3. Select `postman_collection.json` file
4. Create an environment with variable:
   - `base_url` = `http://localhost:8080`
   - `token` = (will be auto-set after login)

### Option B: Manual Testing

#### 1. Register a User
```
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123",
  "firstName": "Test",
  "lastName": "User"
}
```

**Copy the token from response!**

#### 2. Login
```
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "testuser",
  "password": "password123"
}
```

**Copy the token from response!**

#### 3. Get Profile (Protected)
```
GET http://localhost:8080/api/profile
Authorization: Bearer <your-token-here>
```

#### 4. Get Dashboard (Protected)
```
GET http://localhost:8080/api/dashboard
Authorization: Bearer <your-token-here>
```

#### 5. Logout
```
POST http://localhost:8080/api/auth/logout
Authorization: Bearer <your-token-here>
```

## Step 3: Git Workflow

```bash
# Initialize git (if not already done)
git init

# Create a new branch
git checkout -b feature/authentication-system

# Add all files
git add .

# Commit
git commit -m "Implement authentication system with JWT, profile, and dashboard"

# Add remote (replace with your repository URL)
git remote add origin <your-repo-url>

# Push branch
git push -u origin feature/authentication-system
```

Then create a Pull Request on GitHub/GitLab/Bitbucket.

## Troubleshooting

- **Port 8080 in use?** Change `server.port` in `application.properties`
- **Build fails?** Ensure Java 17+ is installed: `java -version`
- **Maven not found?** Install Maven or use Maven Wrapper

## Next Steps

- Test all endpoints with Postman
- Review the code
- Create Pull Request
- Share for code review



