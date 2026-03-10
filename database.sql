-- ============================================================
--  Job Portal Database Setup Script
--  Run this in MySQL before starting the application
-- ============================================================

CREATE DATABASE IF NOT EXISTS jobportal;
USE jobportal;

-- Users table
CREATE TABLE IF NOT EXISTS register (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(16)  NOT NULL,
    gender   VARCHAR(10)  NOT NULL,
    field    VARCHAR(200),
    city     VARCHAR(50)  NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- User profile about/skills
CREATE TABLE IF NOT EXISTS about_user (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    email  VARCHAR(100) NOT NULL,
    about  TEXT,
    skills TEXT,
    FOREIGN KEY (email) REFERENCES register(email) ON DELETE CASCADE
);

-- Profile pictures
CREATE TABLE IF NOT EXISTS profile_pics (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    path  VARCHAR(200) DEFAULT 'profilepic.png',
    FOREIGN KEY (email) REFERENCES register(email) ON DELETE CASCADE
);

-- Education details
CREATE TABLE IF NOT EXISTS education (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    email       VARCHAR(100) NOT NULL,
    school      VARCHAR(200),
    degree      VARCHAR(100),
    year        VARCHAR(20),
    grade       VARCHAR(10),
    description TEXT
);

-- Experience details
CREATE TABLE IF NOT EXISTS experience (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    email       VARCHAR(100) NOT NULL,
    company     VARCHAR(200),
    location    VARCHAR(100),
    year        VARCHAR(20),
    job_title   VARCHAR(100),
    description TEXT
);

-- Resumes
CREATE TABLE IF NOT EXISTS resumes (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    path  VARCHAR(300)
);

-- Jobs
CREATE TABLE IF NOT EXISTS jobs (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    job_profile     VARCHAR(200) NOT NULL,
    company         VARCHAR(200) NOT NULL,
    experience      VARCHAR(50),
    salary          VARCHAR(50),
    no_of_openings  VARCHAR(20),
    job_location    VARCHAR(100),
    time_venue      VARCHAR(200),
    description     TEXT,
    c_email         VARCHAR(100),
    c_person_name   VARCHAR(100),
    c_person_profile VARCHAR(100),
    c_phone_no      VARCHAR(20),
    date1           VARCHAR(20),
    time1           VARCHAR(20)
);

-- Applied jobs
CREATE TABLE IF NOT EXISTS applied_jobs (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    email  VARCHAR(100) NOT NULL,
    jobid  VARCHAR(20)  NOT NULL,
    date1  VARCHAR(20),
    time1  VARCHAR(20)
);

-- Company registrations
CREATE TABLE IF NOT EXISTS company_reg (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    c_name   VARCHAR(200),
    c_email  VARCHAR(100) UNIQUE,
    c_logo   VARCHAR(300) DEFAULT 'company_logo.png',
    c_phoneno VARCHAR(20)
);

-- Contact us messages
CREATE TABLE IF NOT EXISTS contact_us (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(100),
    email   VARCHAR(100),
    subject VARCHAR(200),
    message TEXT,
    date1   VARCHAR(20),
    time1   VARCHAR(20)
);

-- ============================================================
--  Sample Jobs Data
-- ============================================================
INSERT INTO jobs (job_profile, company, experience, salary, no_of_openings, job_location, time_venue, description, c_email, c_person_name, c_person_profile, c_phone_no, date1, time1)
VALUES
('Java Software Developer',   'Infosys',  '2-5 Years',  '6,00,000 - 10,00,000 PA', '50',  'Bangalore',    '15th Jan 2025 - Infosys Campus',  'Urgent requirement for Java developers with Spring Boot experience.',    'hr@infosys.com',     'Priya Sharma',  'HR Manager',    '9876543210', '01-01-2025', '10:00:00'),
('Python Data Analyst',       'Wipro',    '1-3 Years',  '5,00,000 - 8,00,000 PA',  '30',  'Hyderabad',    '20th Jan 2025 - Wipro Office',    'Looking for Python developers skilled in data analysis and ML.',         'recruit@wipro.com',  'Rahul Mehta',   'Talent Lead',   '9123456789', '02-01-2025', '11:00:00'),
('React Frontend Developer',  'TCS',      '2-4 Years',  '7,00,000 - 12,00,000 PA', '20',  'Pune',         '25th Jan 2025 - TCS Innovation',  'React.js, Redux, REST APIs experience required.',                       'jobs@tcs.com',       'Anjali Singh',  'HR Executive',  '9988776655', '03-01-2025', '09:30:00'),
('DevOps Engineer',           'IBM India','3-6 Years',  '10,00,000 - 18,00,000 PA','15',  'Delhi',        '28th Jan 2025 - IBM Campus',      'AWS, Docker, Kubernetes experience required.',                          'careers@ibm.in',     'Suresh Kumar',  'HR Director',   '9001122334', '04-01-2025', '10:30:00'),
('Mobile App Developer',      'Accenture','1-4 Years',  '6,00,000 - 11,00,000 PA', '25',  'Mumbai',       '30th Jan 2025 - Accenture Office','Android / iOS development with Flutter or React Native.',               'hr@accenture.in',    'Meera Patel',   'Recruiter',     '9876001122', '05-01-2025', '14:00:00'),
('Full Stack Developer',      'Cognizant','3-7 Years',  '9,00,000 - 15,00,000 PA', '40',  'Chennai',      '1st Feb 2025 - Cognizant Park',   'Node.js + React or Angular full stack development.',                    'talent@cognizant.com','Vikram Rao',   'Senior HR',     '9445566778', '06-01-2025', '10:00:00'),
('Database Administrator',    'HCL Tech', '4-8 Years',  '8,00,000 - 14,00,000 PA', '10',  'Noida',        '5th Feb 2025 - HCL Noida',        'MySQL, Oracle DB administration and performance tuning.',               'jobs@hcltech.com',   'Nisha Gupta',   'HR Specialist', '9811223344', '07-01-2025', '11:30:00'),
('Cloud Solutions Architect', 'Tech Mahindra','6-10 Years','15,00,000 - 25,00,000 PA','8', 'Hyderabad',   '10th Feb 2025 - TechM Campus',    'AWS/Azure certified architect with enterprise cloud experience.',       'careers@techmahindra.com','Amit Verma','Delivery Manager','9977554433','08-01-2025','09:00:00');

-- Sample Companies
INSERT INTO company_reg (c_name, c_email, c_logo, c_phoneno)
VALUES
('Infosys',        'hr@infosys.com',    'company_logo.png', '080-22741111'),
('Wipro',          'recruit@wipro.com', 'company_logo.png', '040-23002000'),
('TCS',            'jobs@tcs.com',      'company_logo.png', '020-66781234');
