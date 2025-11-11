**My Course Management API Project**

I built a Course Management API using Spring Boot. This is the backend "brains" for a website that organizes instructors, their courses, and the students enrolled in those courses. My API can create, read, update, and delete all this information.

**What I Did?**

**Set Up the Project**: I used Spring Tools in Eclipse to build a new Spring Boot project. I added the tools I'd need for the web (to make API links) and for talking to a database (JPA and MySQL).

**Made the Database**: I opened MySQL Workbench and created one single, empty database called course_management_db.

**Wrote the Models**:

I created three Java classes: Instructor, Course, and Student.

I used @Entity to tell Spring Boot to turn these into tables in my database.

**Linked the Tables (Relationships)**:

One-to-Many: I linked Instructor to Course (One instructor can teach many courses).

Many-to-Many: I linked Course to Student (A course can have many students, and a student can take many courses). Spring Boot automatically created a fourth "join table" to handle this.

**Made the Repositories**: I created three simple "repository" files. These give my app all the basic commands to save, find, and delete things from the database tables.

**Built the Controllers**:

I created three "controller" files to act as the main brain.

They create all the public API web links (like /api/instructors or /api/students).

I wrote functions for all CRUD operations (Create, Read, Update, Delete) for my instructors and students.

I also made special links to enroll a student in a course (POST /api/courses/{courseId}/students/{studentId}).

**Created the Swagger**:

I added a tool called Swagger (springdoc-openapi) that automatically scans my code.

It built a live website (at localhost:8080/swagger-ui/index.html) that documents every API link and lets me test them.

**Troubleshooting**:

Port in Use: I fixed the "Port 8080 was already in use" error by finding and stopping the old, stuck program.

Security Login Page: I saw a login page because I added Spring Security. I fixed it by creating a SecurityConfig.java file to tell Spring to let everyone see my API.

Swagger Not Working: My API wasn't showing up at first. I fixed this by moving all my packages (config, controller, model, repository) inside the main com.unm.api package so Spring Boot could find them.

**Tested Everything (The Final Step)**: I used the Swagger website to test every single API link. I created instructors, added students, made a course, enrolled the students, and then deleted everything to prove all my project requirements worked.
