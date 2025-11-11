package com.unm.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unm.api.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}