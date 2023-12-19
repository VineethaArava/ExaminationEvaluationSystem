package com.examination.app.repository;

import com.examination.app.entity.ExamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

 // Repository interface for managing ExamResponse entities in the database.
@Repository
public interface ExamResponseRepository extends JpaRepository<ExamResponse, Integer> {

    
    //Retrieves a list of ExamResponse entities by student ID and exam paper ID.
    
    List<ExamResponse> findByStudent_IdAndExamPaper_Exam_Id(Integer studentId, Integer examPaperId);

	
}
