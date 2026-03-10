# Job Portal - Rewritten Project
**Based on Smart Programming tutorial by Deepak | Rewritten for resume quality**

---

## What Was Improved

### Java Code Fixes
| Issue | Before | After |
|---|---|---|
| SQL Injection | String concatenation in LIKE queries | `PreparedStatement` with `?` parameters |
| DB Connection | Shared static field (thread-unsafe) | New connection per request |
| Error handling | Bare `out.print(e)` | Proper redirect + error message |
| ValidationFilter | Forward to JSP on fail | Shows actual error message |
| `DownloadResume` | Char-by-char read with PrintWriter | Buffered byte stream via `OutputStream` |
| `PathDetails` | Hardcoded `E:\Projects\...` paths | Centralized, easily configurable |
| Unused imports | `import java.sql.DriverManager` in many files | Cleaned up |

### JSP Improvements
- Replaced all `<jsp:scriptlet>` / `<jsp:expression>` tags with modern `<% %>` and `<%= %>`
- `register.jsp` — added more cities, shows server-side error messages
- `profile.jsp` — fully rewritten with clean Java code, no mixed tag styles
- `index.jsp` — jobs sorted newest first, shows "No jobs" message if DB empty
- `header/profileheader` — clean, consistent structure

---

## Setup Instructions

### 1. Database
```sql
-- Run this in MySQL Workbench or CLI
source database.sql
```

### 2. Update PathDetails.java
Edit `src/java/com/deepak/backend/PathDetails.java` and change `BASE` to your actual Tomcat deployment path:
```java
private static final String BASE = "C:/Program Files/Apache/Tomcat9/webapps/JobPortal/";
```

### 3. Update db.properties
Edit `src/java/com/deepak/connection/db.properties`:
```properties
jdbc-url=jdbc:mysql://localhost:3306/jobportal
username=root
password=your_password
```

### 4. (Optional) Email — SendConfirmationMail.java
Update `FROM_EMAIL` and `FROM_PASSWORD` in `SendConfirmationMail.java`.
Use a Gmail App Password (not your regular password).

### 5. Import into NetBeans / Eclipse
- **NetBeans**: File → Open Project → select the `JobPortal` folder
- **Eclipse**: File → Import → Web → War File (or Existing Projects)

---

## Project Structure
```
JobPortal/
├── src/java/com/deepak/
│   ├── backend/           ← 26 Servlet classes
│   └── connection/        ← DbConnection + db.properties
├── web/
│   ├── *.jsp              ← 25 JSP pages
│   ├── css/style.css
│   ├── images/
│   ├── profilepics/
│   ├── resumes/
│   ├── resumes-builder/
│   ├── company-logo/
│   └── WEB-INF/
│       ├── web.xml
│       └── lib/           ← JAR dependencies
├── database.sql           ← Run this first!
└── README.md
```

## Key Features
- User Registration & Login (with Remember Me cookies)
- User Profile (photo, education, experience, skills)
- Job Search by Technology (AJAX live search)
- Job Search by Location (AJAX live search)
- Job Search with combined Technology + Location filter
- Apply for Jobs / Delete Applications
- My Applied Jobs page
- Company Listings page
- Resume Upload & Download
- PDF Resume Builder (using iText)
- Contact Us with email confirmation
- Server-side form validation (filter + regex)
