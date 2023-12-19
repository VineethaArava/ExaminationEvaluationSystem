package com.examination.app.controller;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.examination.app.dto.AnswerDTO;
import com.examination.app.dto.AnswerOptionDTO;
import com.examination.app.entity.Exam;
import com.examination.app.entity.Student;
import com.examination.app.exception.ResourceAlreadyExistException;
import com.examination.app.repository.ExamPaperRepository;
import com.examination.app.repository.ExamRepository;
import com.examination.app.repository.ExamResponseRepository;
import com.examination.app.repository.ExamResultRepository;
import com.examination.app.repository.StudentRepository;
import com.examination.app.service.StudentService;
import com.examination.app.service.UserService;
public class StudentServiceTest {
    @Mock
    private ExamRepository examRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private ExamPaperRepository examPaperRepository;
    @Mock
    private ExamResponseRepository examResponseRepository;
    @Mock
    private ExamResultRepository examResultRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private StudentService studentService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testSubmitExamAlreadyTaken() {
        // Arrange
        int studentId = 1;
        int examId = 1;
        Student student = new Student(studentId, "John Doe", "Address", "123456789");
        Exam exam = new Exam(examId, "Mock Exam", 1.5f, "10:00 AM", 10, false);
        AnswerDTO answerDTO = new AnswerDTO(studentId, examId, Collections.singletonList(new AnswerOptionDTO(1, "OptionA")));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(examRepository.findById(examId)).thenReturn(Optional.of(exam));
        when(examResultRepository.existsByStudentAndExam(student, exam)).thenReturn(true);
        // Act and Assert
        assertThrows(ResourceAlreadyExistException.class, () -> studentService.submitExam(answerDTO));
        verify(examResponseRepository, never()).save(any());
        verify(examResultRepository, never()).save(any());
    }
    // Add more tests as needed for other methods in StudentService
}
