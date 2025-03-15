# Resume Analyzer

A **Spring Boot**-based AI-powered Resume Analyzer that evaluates resumes, provides feedback, and suggests job matches.

## Features

✅ **AI-Powered Resume Scoring**: Analyzes resumes and assigns a score.  
✅ **Detailed Feedback**: Identifies strengths and areas for improvement.  
✅ **Job Match Suggestions**: Recommends suitable job roles based on resume content.  
✅ **Email Report**: Sends analysis results via email.  
✅ **Simple Upload System**: Users can upload resumes without authentication.

## Technologies Used

- **Java 23**
- **Spring Boot (Backend)**
- **Hibernate & JPA (Database ORM)**
- **MySQL (Database Storage)**
- **HTML&CSS & JavaScript (Frontend)**
- **Maven (Build Tool)**
- **Google Gemini API (AI Processing)**
- **Spring Mail (Email Service)**

## Setup & Installation

### 1️⃣ Clone the Repository

```sh
git clone https://github.com/Kaloyanov5/resume-analyzer.git
cd resume-analyzer
```

### 2️⃣ Database Setup

Ensure you have MySQL installed and running.

Run the schema setup script:

```sql
CREATE DATABASE resume_analyzer;
```

### 3️⃣ Configure `application.properties`

#### Create an `application.properties` file in `src/main/resources/`. ####

Copy the contents of `application.properties.example` and assign the following values:

```
spring.datasource.url=jdbc:mysql://localhost:port/database_name
spring.datasource.username=username
spring.datasource.password=password
gemini.api.key=gemini_api_key
secret.key=secret_key
spring.mail.username=your_email@gmail.com
spring.mail.password=your_email_password
gemini.initial.prompt=prompt(find in README.md)
```

#### Gemini Inital Prompt (Set to `gemini.initial.prompt`) ####

You can edit the prompt if you want different result or give Gemini different instructions on analyzating:
```
You are an AI resume evaluator.
Your task is to analyze the given resume and assign a score from 0 to 100 based on the following criteria.
I have included a example response from you (json format).
Scoring Criteria:
Relevance to Industry (25 points) Does the resume align with a specific industry or job role? Are key skills and experiences relevant to common job requirements?
Clarity & Formatting (20 points) Is the resume well-structured with clear sections (Education, Experience, Skills, etc.)? Is it easy to read with proper bullet points, spacing, and font consistency?
Work Experience & Achievements (25 points) Does it showcase relevant work experience? Are achievements quantified with metrics (e.g., "Increased sales by 30%")?
Skills & Certifications (15 points) Does the candidate list technical and soft skills? Are there certifications relevant to the job role?
Grammar & Professionalism (15 points) Are there any grammatical errors or typos? Is the language professional and well-articulated?
Final Score Calculation:  Sum up the individual category scores to get a final number from 0 to 100.
Example Output (JSON FORMAT ONLY): "
{
     "total_score": 85,
     "category_scores": {
       "relevance": 22,
       "clarity": 18,
       "experience": 20,
       "skills": 12,
       "grammar": 13
     },
     "feedback": {
       "strengths": [
         "Well-structured resume with clear headings.",
         "Good use of quantifiable achievements in work experience."
       ],
       "areas_for_improvement": [
         "Consider adding more relevant certifications.",
         "Some minor grammar issues in the summary section."
       ]
     },
     "job_match_suggestions": [
       "Software Engineer",
       "Data Analyst",
       "Backend Developer"
     ]
}
"
```

### 4️⃣ Install Dependencies

Run the following command to install backend dependencies:

```sh
mvn clean install
```

### 5️⃣ Run the Application

Using Maven:

```sh
mvn spring-boot:run
```

Or using IntelliJ IDEA:

- Open the project in IntelliJ.
- Run `ResumeAnalyzerApplication.java`.

## Usage

1. Open the web application.
2. Upload a resume file.
3. Enter an email address for receiving the results.
4. View AI-generated feedback and job suggestions.
5. Receive a detailed email report.

## License

This project is open-source and available under the **MIT License**.

## Contributing

Pull requests are welcome! Please open an issue for major changes before submitting PRs.
