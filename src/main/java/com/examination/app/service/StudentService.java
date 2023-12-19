package com.examination.app.service;

import com.examination.app.dto.AnswerDTO;
import com.examination.app.dto.AnswerOptionDTO;
import com.examination.app.dto.StudentRegisterDTO;
import com.examination.app.entity.*;
import com.examination.app.exception.ResourceAlreadyExistException;
import com.examination.app.exception.ResourceNotFoundException;
import com.examination.app.repository.*;
import com.examination.app.util.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class StudentService  {

    @Autowired
    ExamPaperRepository examPaperRepository;
    @Autowired
     ExamResultRepository examResultRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ExamResponseRepository examResponseRepository;

    @Autowired
    UserService userService;


    
    public List<ExamPaper> giveExam(Integer examId) {
        // Retrieve the exam papers for a given exam
        return examPaperRepository.findByExam_Id(examId);
    }

    
    public ExamResult checkResult(Integer examId, Integer studentId) {
        // Check and retrieve the exam result for a given exam and student
        Exam exam = examRepository.findById(examId).orElseThrow(ResourceNotFoundException::new);
        if(!exam.getResultDeclared()) throw new ResourceNotFoundException("Result not declared yet");
        return examResultRepository.findByExam_IdAndStudent_Id(examId,studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }

    // @Transactional
    public Exam submitExam(AnswerDTO answerDTO) {
        // Submit exam answers and calculate marks
        Student student = studentRepository.findById(answerDTO.getStudentId()).orElseThrow(ResourceNotFoundException::new);
        Exam exam = examRepository.findById(answerDTO.getExamId()).orElseThrow(() -> new ResourceNotFoundException("Exam id not found"));
        if (examResultRepository.existsByStudentAndExam(student,exam)) throw new ResourceAlreadyExistException("Exam already taken");

        AtomicReference<Integer> marks = new AtomicReference<>(0);
        // Iterate through answers and save corresponding exam responses
        answerDTO.getAnswers().stream().forEach(answer -> {
            ExamPaper examPaper = examPaperRepository.findById(answer.getQuestionId()).orElseThrow(ResourceNotFoundException::new);
            ExamResponse er = new ExamResponse(null, student,examPaper,answer.getSelectedAnswer(),examPaper.getCorrectOption().equals(answer.getSelectedAnswer()));
            if(examPaper.getCorrectOption().equals(answer.getSelectedAnswer())) marks.updateAndGet(v -> v + examPaper.getMark());
            examResponseRepository.save(er);
        });
        // Save the overall exam result
        ExamResult examResult = new ExamResult(null,convertToGrade(marks.get()),(float) marks.get(), student, exam);
        examResultRepository.save(examResult);
        return exam;
    }

   
    public Student register(StudentRegisterDTO r) {
        // Register a new student
        r.setId(null);
        r.setRole(UserRoleEnum.STUDENT);
        userService.registerUser(r);
        Student student = new Student(null, r.getName(), r.getAddress(), r.getContact());
        studentRepository.save(student);
        return student;
    }

    private String convertToGrade(Integer score) {
        // Convert numeric score to a corresponding grade
        if (score < 0 || score > 100) {
            return "Invalid score. Please enter a score between 0 and 100.";
        } else if (score >= 90) {
            return "A+";
        } else if (score >= 85) {
            return "A";
        } else if (score >= 80) {
            return "A-";
        } else if (score >= 75) {
            return "B+";
        } else if (score >= 70) {
            return "B";
        } else if (score >= 65) {
            return "B-";
        } else if (score >= 60) {
            return "C+";
        } else if (score >= 55) {
            return "C";
        } else if (score >= 50) {
            return "C-";
        } else if (score >= 45) {
            return "D+";
        } else if (score >= 40) {
            return "D";
        } else {
            return "F-";
        }
    }

	
	public List<ExamResult> getResultsByExam(Integer examId) {
		// TODO Auto-generated method stub
		return examResultRepository.findByExam_Id(examId);
	}
}
