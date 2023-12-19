package com.examination.app.controller;

import com.examination.app.dto.AnswerDTO;

import com.examination.app.dto.BasicDTO;
import com.examination.app.dto.StudentRegisterDTO;
import com.examination.app.entity.Exam;
import com.examination.app.entity.ExamPaper;
import com.examination.app.entity.ExamResult;
import com.examination.app.entity.Student;

import com.examination.app.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    // Autowired service responsible for student-related operations.
    @Autowired
    StudentService studentService;

    // Register a new student based on the provided student registration details.
    @PostMapping("/register")
    public ResponseEntity<Student> register(@RequestBody StudentRegisterDTO studentRegisterDTO) {
        return ResponseEntity.ok(studentService.register(studentRegisterDTO));
    }

    // Submit exam answers and return a response indicating the success of the operation.
    @PostMapping("/submitExam")
    public ResponseEntity<BasicDTO<Exam>> submitExam(@RequestBody AnswerDTO answers) {
        return ResponseEntity.ok(new BasicDTO<>(true, "Exam successfully submitted", studentService.submitExam(answers)));
    }

    // Retrieve exam papers for a given exam based on the exam ID.
    @GetMapping("/giveExam/{examId}")
    public ResponseEntity<List<ExamPaper>> giveExam(@PathVariable("examId") Integer examId) {
        return ResponseEntity.ok(studentService.giveExam(examId));
    }

    // Check the result of a student for a specific exam based on the exam and student IDs.
    @GetMapping("/examResult/{examId}/{studentId}")
    public ResponseEntity<ExamResult> examResult(@PathVariable("examId") Integer examId, @PathVariable("studentId") Integer studentId) {
        return ResponseEntity.ok(studentService.checkResult(examId, studentId));
    }
}
