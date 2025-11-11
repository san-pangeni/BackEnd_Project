package com.unm.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unm.api.model.Course;
import com.unm.api.model.Student;
import com.unm.api.repository.CourseRepository;
import com.unm.api.repository.InstructorRepository;
import com.unm.api.repository.StudentRepository;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private InstructorRepository instructorRepository;
    
    @Autowired
    private StudentRepository studentRepository;

    // --- Basic CRUD for Course ---

    // CREATE a course and assign it to an instructor
    // This handles the One-to-Many relationship creation
    @PostMapping("/instructors/{instructorId}")
    public ResponseEntity<Course> createCourse(@PathVariable Long instructorId, @RequestBody Course course) {
        return instructorRepository.findById(instructorId)
                .map(instructor -> {
                    course.setInstructor(instructor);
                    return ResponseEntity.ok(courseRepository.save(course));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // READ all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // READ one course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE a course (e.g., change its title)
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setTitle(courseDetails.getTitle());
                    return ResponseEntity.ok(courseRepository.save(course));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE a course
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(course -> {
                    courseRepository.delete(course);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // --- CRUD for Many-to-Many Relationship (Student <-> Course) ---
    
    // CREATE: Enroll a student in a course
    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<?> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (course == null || student == null) {
            return ResponseEntity.notFound().build();
        }

        course.addStudent(student); // Use the helper method
        courseRepository.save(course);
        return ResponseEntity.ok().build();
    }

    // DELETE: Remove a student from a course
    @DeleteMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<?> removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (course == null || student == null) {
            return ResponseEntity.notFound().build();
        }

        course.removeStudent(student); // Use the helper method
        courseRepository.save(course);
        return ResponseEntity.ok().build();
    }
}