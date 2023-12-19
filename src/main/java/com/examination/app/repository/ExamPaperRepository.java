package com.examination.app.repository;

import com.examination.app.entity.ExamPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository interface for managing ExamPaper entities in the database.
@Repository
public interface ExamPaperRepository extends JpaRepository<ExamPaper, Integer> {

    //Retrieves a list of ExamPaper entities based on the associated Exam's ID.
    
    List<ExamPaper> findByExam_Id(Integer id);
}
