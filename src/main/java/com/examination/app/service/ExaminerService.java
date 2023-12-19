package com.examination.app.service;

import com.examination.app.entity.Exam;
import com.examination.app.entity.ExamPaper;
import com.examination.app.entity.ExamResponse;
import com.examination.app.entity.ExamResult;
import com.examination.app.exception.ResourceNotFoundException;
import com.examination.app.repository.ExamPaperRepository;
import com.examination.app.repository.ExamRepository;
import com.examination.app.repository.ExamResponseRepository;
import com.examination.app.repository.ExamResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


 //Service class for handling examiner-related operations.
 
@Service
public class ExaminerService {

    private static final Logger log = LoggerFactory.getLogger(ExaminerService.class);

    @Autowired
    private ExamResponseRepository examResponseRepository;
    
    @Autowired
    ExamPaperRepository examPaperRepository;
    
    @Autowired
    ExamResultRepository examResultRepository;

    @Autowired
    ExamRepository examRepository;

    @Autowired
    ExamPaperService examPaperService;

   
    public Exam prepareTestPaper(Exam exam) {
        exam.setId(null);
        exam.setResultDeclared(false);
        log.info("SAVING EXAM");
        examRepository.save(exam);
        log.info("EXAM SAVED");
        return exam;
    }

   
    
    public Exam modifyTestPaper(Exam exam) {
        log.info("UPDATING EXAM");
        examRepository.save(exam);
        log.info("EXAM UPDATED");
        return exam;
    }

    
    public Exam declareResult(Integer examId) {
        log.info("SAVING EXAM RESULT");
        Exam exam = examRepository.findById(examId).orElseThrow(ResourceNotFoundException::new);
        exam.setResultDeclared(true);
        examRepository.save(exam);
        log.info("EXAM RESULT SAVED");
        return exam;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    
    public ExamPaper addQuestionToExam(ExamPaper examPaper) {
        return examPaperService.createTestPaper(examPaper);
    }

   
    public List<ExamResult> findCopiesByExam(Integer examId) {
        return examResultRepository.findByExam_Id(examId);
    }

   
    public List<ExamResponse> findCopiesByExamStudent(Integer examId, Integer studentId) {
        return examResponseRepository.findByStudent_IdAndExamPaper_Exam_Id(studentId, examId);
    }
}
