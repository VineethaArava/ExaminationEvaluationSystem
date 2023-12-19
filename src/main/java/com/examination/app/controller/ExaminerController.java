package com.examination.app.controller;

import com.examination.app.entity.Exam;

import com.examination.app.entity.ExamPaper;
import com.examination.app.entity.ExamResponse;
import com.examination.app.entity.ExamResult;
import com.examination.app.service.ExaminerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examiner")
public class ExaminerController {

    // Autowired service responsible for examiner-related operations.
    @Autowired
    ExaminerService examinerService;

    // Retrieve all exams.
    @GetMapping("/getAllExams")
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examinerService.getAllExams());
    }

    // Create a new exam and return the created exam.
    @PostMapping("/createExam")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(examinerService.prepareTestPaper(exam));
    }

    // Update an existing exam and return the updated exam.
    @PutMapping("/updateExam")
    public ResponseEntity<Exam> updateExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(examinerService.modifyTestPaper(exam));
    }

    // Add a question to an existing exam paper and return the updated exam paper.
    @PostMapping("/addQuestionToExam")
    public ResponseEntity<ExamPaper> addQuestionToExam(@RequestBody ExamPaper examPaper) {
        return ResponseEntity.ok(examinerService.addQuestionToExam(examPaper));
    }

    // Find copies of an exam based on the exam ID.
    @GetMapping("/findCopiesByExam/{examId}")
    public ResponseEntity<List<ExamResult>> findCopiesByExam(@PathVariable("examId") Integer examId) {
        return ResponseEntity.ok(examinerService.findCopiesByExam(examId));
    }

    // Find copies of an exam for a specific student based on the exam and student IDs.
    @GetMapping("/findCopiesByExamStudent/{examId}/{studentId}")
    public ResponseEntity<List<ExamResponse>> findCopiesByExamStudent(@PathVariable("examId") Integer examId, @PathVariable("studentId") Integer studentId) {
        return ResponseEntity.ok(examinerService.findCopiesByExamStudent(examId, studentId));
    }

    // Declare the result of an exam based on the exam ID.
    @GetMapping("/declareResult/{examId}")
    public ResponseEntity<Exam> declareResult(@PathVariable("examId") Integer examId) {
        return ResponseEntity.ok(examinerService.declareResult(examId));
    }
}
