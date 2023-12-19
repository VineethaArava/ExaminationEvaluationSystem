package com.examination.app.repository;

import com.examination.app.entity.Exam;
import com.examination.app.entity.ExamResult;
import com.examination.app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing ExamResult entities in the database.
 */
@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Integer> {

    /**
     * Retrieves a list of ExamResult entities by exam ID.
     *
     * @param examId The ID of the exam.
     * @return A list of ExamResult entities matching the specified exam ID.
     */
    List<ExamResult> findByExam_Id(Integer examId);

    /**
     * Retrieves an Optional ExamResult entity by exam ID and student ID.
     *
     * @param examId The ID of the exam.
     * @param studentId The ID of the student.
     * @return An Optional ExamResult entity matching the specified exam ID and student ID.
     */
    Optional<ExamResult> findByExam_IdAndStudent_Id(Integer examId, Integer studentId);

    /**
     * Checks if an ExamResult exists for a specific student and exam combination.
     *
     * @param student The student entity.
     * @param exam The exam entity.
     * @return True if an ExamResult exists, otherwise false.
     */
    boolean existsByStudentAndExam(Student student, Exam exam);
}
