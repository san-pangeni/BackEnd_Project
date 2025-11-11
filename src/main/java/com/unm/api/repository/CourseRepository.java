package com.unm.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unm.api.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}