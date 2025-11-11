# Step-by-Step Guide: Test APIs with Postman

## Prerequisites
- ✅ Spring Boot application is running on `http://localhost:8080`
- ✅ Postman installed (download from https://www.postman.com/downloads/)

---

## Step 1: Import Postman Collection

1. **Open Postman**
   - Launch the Postman application

2. **Import Collection**
   - Click **"Import"** button (top left corner)
   - Click **"Upload Files"** or drag and drop
   - Navigate to your project folder: `C:\Authenticationssss`
   - Select `postman_collection.json`
   - Click **"Import"**

3. **Verify Collection**
   - You should see "Authentication System API" collection in the left sidebar
   - Expand it to see:
     - Authentication folder (Register, Login, Logout)
     - Profile folder (Get Profile)
     - Dashboard folder (Get Dashboard)

---

## Step 2: Set Up Environment Variables (Recommended)

1. **Create Environment**
   - Click **"Environments"** in the left sidebar (or click the eye icon at top right)
   - Click **"+"** button to create new environment
   - Name it: `Local Development`

2. **Add Variables**
   - Click **"Add"** to add variables:
   
   **Variable 1:**
   - Variable: `base_url`
   - Initial Value: `http://localhost:8080`
   - Current Value: `http://localhost:8080`
   
   **Variable 2:**
   - Variable: `token`
   - Initial Value: (leave empty)
   - Current Value: (leave empty)

3. **Select Environment**
   - Click on the environment dropdown (top right)
   - Select **"Local Development"**

---

## Step 3: Test Register User API

1. **Open Register Request**
   - Navigate to: `Authentication System API` → `Authentication` → `Register User`

2. **Check Request Details**
   - Method: `POST`
   - URL: Should show `{{base_url}}/api/auth/register`
   - Headers: `Content-Type: application/json` (should be auto-set)

3. **Set Request Body**
   - Click **"Body"** tab
   - Select **"raw"** radio button
   - Select **"JSON"** from dropdown
   - Enter this JSON:
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

4. **Send Request**
   - Click **"Send"** button
   - Wait for response

5. **Check Response**
   - Status: Should be `201 Created` (green)
   - Response body should contain:
   ```json
   {
     "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
     "type": "Bearer",
     "username": "testuser",
     "email": "test@example.com",
     "message": "Registration successful"
   }
   ```
   - **IMPORTANT:** Copy the `token` value from the response!

---

## Step 4: Test Login API

1. **Open Login Request**
   - Navigate to: `Authentication System API` → `Authentication` → `Login`

2. **Set Request Body**
   - Click **"Body"** tab
   - Select **"raw"** and **"JSON"**
   - Enter this JSON:
   ```json
   {
     "username": "testuser",
     "password": "password123"
   }
   ```

3. **Send Request**
   - Click **"Send"**
   - Status: Should be `200 OK`

4. **Check Response**
   - Response should contain a new JWT token
   - **Copy the token** from the response
   - The Login request has a script that automatically saves the token to environment (if configured)

5. **Verify Token Saved**
   - Click the eye icon (top right) to see environment variables
   - Check if `token` variable has been updated

---

## Step 5: Test Get Profile API (Protected Endpoint)

1. **Open Get Profile Request**
   - Navigate to: `Authentication System API` → `Profile` → `Get Profile`

2. **Check Authorization**
   - Click **"Authorization"** tab
   - Type: Should be **"Bearer Token"**
   - Token: Should show `{{token}}` (uses environment variable)
   - If not set, manually paste your token here

3. **Send Request**
   - Click **"Send"**
   - Status: Should be `200 OK`

4. **Check Response**
   - Response should contain user profile:
   ```json
   {
     "id": 1,
     "username": "testuser",
     "email": "test@example.com",
     "firstName": "Test",
     "lastName": "User",
     "phoneNumber": "1234567890",
     "role": "USER",
     "createdAt": "2025-11-10T20:16:33",
     "updatedAt": "2025-11-10T20:16:33"
   }
   ```

---

## Step 6: Test Get Dashboard API (Protected Endpoint)

1. **Open Get Dashboard Request**
   - Navigate to: `Authentication System API` → `Dashboard` → `Get Dashboard`

2. **Check Authorization**
   - Click **"Authorization"** tab
   - Should use `{{token}}` automatically

3. **Send Request**
   - Click **"Send"**
   - Status: Should be `200 OK`

4. **Check Response**
   - Response should contain dashboard data:
   ```json
   {
     "welcomeMessage": "Welcome to your dashboard, Test!",
     "username": "testuser",
     "email": "test@example.com",
     "role": "USER",
     "lastLogin": "2025-11-10T20:16:33",
     "status": "Active"
   }
   ```

---

## Step 7: Test Logout API

1. **Open Logout Request**
   - Navigate to: `Authentication System API` → `Authentication` → `Logout`

2. **Check Authorization**
   - Authorization tab should have `{{token}}`

3. **Send Request**
   - Click **"Send"**
   - Status: Should be `200 OK`

4. **Check Response**
   - Response: `{"message": "Logout successful"}`

---

## Troubleshooting

### Error: "Connection refused"
- **Solution:** Make sure Spring Boot application is running
- Check console for: `Started AuthenticationSystemApplication`

### Error: "401 Unauthorized"
- **Solution:** Token might be expired or invalid
- Re-login to get a new token
- Make sure token is in format: `Bearer <your-token>`

### Error: "400 Bad Request"
- **Solution:** Check request body JSON format
- Ensure all required fields are present
- Check for typos in field names

### Token not saving automatically
- **Solution:** Manually copy token from response
- Paste it in the Authorization tab of protected endpoints
- Or update the `token` environment variable manually

---

## Success Checklist

- [ ] Collection imported successfully
- [ ] Environment variables configured
- [ ] Register user returns 201 with token
- [ ] Login returns 200 with token
- [ ] Get Profile returns 200 with user data
- [ ] Get Dashboard returns 200 with dashboard data
- [ ] Logout returns 200

---

## Next Steps

After successful testing, proceed to:
- **Push branch & PR** (see `GIT_WORKFLOW_GUIDE.md`)



