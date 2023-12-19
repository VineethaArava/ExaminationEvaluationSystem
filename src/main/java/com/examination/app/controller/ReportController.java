package com.examination.app.controller;

import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examination.app.entity.ExamPaper;
import com.examination.app.entity.ExamResponse;
import com.examination.app.entity.ExamResult;
import com.examination.app.entity.Student;
import com.examination.app.service.ExaminerService;

import com.examination.app.service.StudentService;


@RestController
@RequestMapping("/api/examiner")
public class ReportController {
	

    @Autowired
    private ExaminerService examinerService;
    

    @Autowired
    private StudentService studentService;

   

    // Find copies of an exam for a specific student based on the exam and student IDs.
    @GetMapping("/report/findCopiesByExamStudent/{examId}/{studentId}")
    public ResponseEntity<Map<ExamPaper, Long>> findCopiesByExamStudent(
            @PathVariable("examId") Integer examId,
            @PathVariable("studentId") Integer studentId) {
        List<ExamResponse> copies = examinerService.findCopiesByExamStudent(examId, studentId);

        // Count copies using Java Stream API
        Map<ExamPaper, Long> copiesCount = copies.stream()
                .collect(Collectors.groupingBy(response -> response.getExamPaper(), Collectors.counting()));

        return ResponseEntity.ok(copiesCount);
    }
	
 // Count the result for each student based on the exam ID
    @GetMapping("/countResultByExam/{examId}")
    public ResponseEntity<Map<Student, Long>> countResultByExam(@PathVariable("examId") Integer examId) {
        List<ExamResult> results = studentService.getResultsByExam(examId);

        // Count results using Java Stream API
        Map<Student, Long> resultCount = results.stream()
                .collect(Collectors.groupingBy(ExamResult::getStudent, Collectors.counting()));

        return ResponseEntity.ok(resultCount);
    }
   

    
}
