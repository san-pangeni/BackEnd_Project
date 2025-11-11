package com.unm.api.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // --- One-to-Many Relationship ---
    // This is the "many" side and owns the relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id") // This creates the instructor_id foreign key column
    private Instructor instructor;
    
    
    // --- Many-to-Many Relationship ---
    // This is the "owning" side of the relationship
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "course_student", // Name of the join table
        joinColumns = @JoinColumn(name = "course_id"), // Foreign key for Course
        inverseJoinColumns = @JoinColumn(name = "student_id") // Foreign key for Student
    )
    private Set<Student> students = new HashSet<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    // --- Helper methods to manage the M-M relationship ---
    public void addStudent(Student student) {
        this.students.add(student);
        student.getCourses().add(this);
    }
    
    public void removeStudent(Student student) {
        this.students.remove(student);
        student.getCourses().remove(this);
    }
}