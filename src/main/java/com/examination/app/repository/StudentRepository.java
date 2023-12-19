package com.examination.app.repository;

import com.examination.app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Student entities in the database.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
