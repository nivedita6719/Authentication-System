# Troubleshoot 403 Forbidden Error - Step by Step

## You Have:
- ✅ Environment "Local" selected
- ✅ Token saved in environment
- ❌ Still getting 403 Forbidden

---

## 🔍 Debugging Steps

### Step 1: Verify Token is Being Sent

1. **Open:** `Profile` → `Get Profile`

2. **Click "Headers" tab** (not Authorization tab)

3. **Look for "Authorization" header**
   - Should show: `Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...`
   - If you see `Authorization: Bearer {{token}}` → Variable not resolving
   - If no Authorization header → Token not being sent

**Fix if variable not resolving:**
- Make sure "Local" environment is selected
- Try pasting actual token directly in Authorization tab (instead of `{{token}}`)

---

### Step 2: Check Token Validity

**The token might be:**
- Expired (tokens expire after 24 hours)
- Invalid format
- From a different user that doesn't exist

**Solution:**
1. **Login again** to get a fresh token
2. **Copy the NEW token** from login response
3. **Update token** in environment
4. **Test again**

---

### Step 3: Verify User Exists

**The token might be valid, but user doesn't exist in database**

1. **Check if you registered the user:**
   - Did you successfully register? (Got 201 response?)
   - Did you use the same username for login?

2. **Try this:**
   - Register a NEW user
   - Login with that user
   - Get token
   - Test Profile/Dashboard

---

### Step 4: Check Spring Boot Console

**Look at your IntelliJ console for errors:**

1. **Start your Spring Boot app** (if not running)

2. **Send the GET Profile request**

3. **Check console output** for:
   - `JWT token validation failed`
   - `Error loading user details`
   - `Token validation failed`
   - Any exception stack traces

**Common errors:**
- `User not found with username: xxx` → User doesn't exist
- `JWT token validation failed` → Token is invalid/expired
- No errors but 403 → Token not being sent correctly

---

### Step 5: Test with Fresh Token

**Complete fresh start:**

1. **Stop Spring Boot app** (if running)

2. **Start Spring Boot app again**

3. **Register a new user:**
   ```
   POST /api/auth/register
   {
     "username": "newuser",
     "email": "new@example.com",
     "password": "password123",
     "firstName": "New",
     "lastName": "User"
   }
   ```

4. **Login:**
   ```
   POST /api/auth/login
   {
     "username": "newuser",
     "password": "password123"
   }
   ```

5. **Copy token** from login response

6. **Update token** in Postman environment

7. **Test Profile:**
   ```
   GET /api/profile
   Authorization: Bearer <your-token>
   ```

---

### Step 6: Manual Token Test

**Instead of using `{{token}}`, paste token directly:**

1. **Get token from login response**

2. **Open:** `Profile` → `Get Profile`

3. **Click "Authorization" tab**

4. **Type:** Select "Bearer Token"

5. **Token field:** Paste the ACTUAL token (not `{{token}}`)
   - Should be: `eyJhbGciOiJIUzI1NiJ9.eyJzdWliOiJ0ZXN0dXNlcilsImlhdCI6...`
   - Full long string, no spaces

6. **Click "Send"**

**If this works:**
- Environment variable is not resolving correctly
- Use actual token for now, or fix environment variable

**If this doesn't work:**
- Token is invalid/expired
- User doesn't exist
- Check Spring Boot console for errors

---

### Step 7: Check Request Headers

**Verify what's actually being sent:**

1. **Open:** `Profile` → `Get Profile`

2. **Click "Headers" tab**

3. **Look for these headers:**
   - `Authorization: Bearer <token>` ← Must be present
   - `Content-Type: application/json` (optional for GET)

4. **If Authorization header is missing:**
   - Authorization tab not configured correctly
   - Re-check Authorization tab settings

---

## 🎯 Quick Fixes

### Fix 1: Use Actual Token (Not Variable)

1. Login → Copy token
2. Open Get Profile
3. Authorization tab → Paste actual token (not `{{token}}`)
4. Send → Should work

### Fix 2: Fresh Start

1. Restart Spring Boot
2. Register new user
3. Login → Get token
4. Update environment
5. Test

### Fix 3: Check User Exists

1. Make sure you registered before logging in
2. Use same username for both register and login
3. If user doesn't exist, register again

---

## ✅ Success Checklist

- [ ] Spring Boot app is running
- [ ] User is registered
- [ ] Login successful (got token)
- [ ] Token copied correctly (full string, no spaces)
- [ ] Token saved in environment
- [ ] Environment "Local" is selected
- [ ] Authorization tab shows token (or `{{token}}`)
- [ ] Headers tab shows "Authorization: Bearer ..."
- [ ] No errors in Spring Boot console
- [ ] GET request returns 200 OK

---

## 🚨 Common Issues & Solutions

### Issue: Token shows `{{token}}` in Headers
**Solution:** Environment not selected or variable not resolving
- Select "Local" environment
- Or paste actual token

### Issue: No Authorization header in Headers tab
**Solution:** Authorization tab not configured
- Go to Authorization tab
- Select "Bearer Token"
- Enter token or `{{token}}`

### Issue: "User not found" in console
**Solution:** User doesn't exist
- Register the user first
- Then login

### Issue: "JWT token validation failed" in console
**Solution:** Token is invalid/expired
- Login again to get fresh token
- Update environment

### Issue: Everything looks correct but still 403
**Solution:** Check Spring Boot console
- Look for any exceptions
- Verify app is running
- Try restarting the app

---

## 📝 Test Sequence

**Follow this exact sequence:**

```
1. Start Spring Boot app
   ↓
2. Register user (POST /api/auth/register)
   → Should get 201 Created
   ↓
3. Login (POST /api/auth/login)
   → Should get 200 OK with token
   → Copy token
   ↓
4. Save token to environment
   → Click eye icon
   → Paste token in "Local" environment
   ↓
5. Select "Local" environment (top right dropdown)
   ↓
6. Test Profile (GET /api/profile)
   → Authorization tab: Bearer Token, {{token}}
   → Click Send
   → Should get 200 OK
```

---

## 🆘 Still Not Working?

1. **Check Spring Boot console** for detailed errors
2. **Try pasting actual token** instead of `{{token}}`
3. **Restart Spring Boot** and try again
4. **Register a completely new user** and test
5. **Check if token is complete** (very long string, no truncation)

If still failing, share:
- Spring Boot console errors
- What you see in Headers tab
- Token format (first/last 20 characters only for security)



