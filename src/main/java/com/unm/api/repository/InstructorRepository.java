package com.unm.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unm.api.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}