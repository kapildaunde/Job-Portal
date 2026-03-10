

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
