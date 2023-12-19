package com.examination.app.repository;

import com.examination.app.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Repository interface for managing Exam entities in the database.
@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
}
