package com.examination.app.controller;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.examination.app.entity.Exam;
import com.examination.app.exception.ResourceNotFoundException;
import com.examination.app.repository.ExamRepository;
import com.examination.app.service.ExamPaperService;
import com.examination.app.service.ExaminerService;
	
	public class ExaminerServiceTest {
	
	    @Mock
	    private ExamRepository examRepository;
	
	    @Mock
	    private ExamPaperService examPaperService;
	
	    @InjectMocks
	    private ExaminerService examinerService;
	
	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }
	
	    @Test
	    public void testPrepareTestPaper() {
	        // Arrange
	        Exam examToSave = new Exam(null, "Mock Exam", 1.5f, "10:00 AM", 10, false);
	        Exam savedExam = new Exam(1, "Mock Exam", 1.5f, "10:00 AM", 10, false);
	        when(examRepository.save(examToSave)).thenReturn(savedExam);
	
	        // Act
	        Exam result = examinerService.prepareTestPaper(examToSave);
	
	        // Assert
	        assertNotNull(result);
	        verify(examRepository, times(1)).save(examToSave);
	    }
	
	    @Test
	    public void testDeclareResult() {
	        // Arrange
	        int examId = 1;
	        Exam existingExam = new Exam(examId, "Mock Exam", 1.5f, "10:00 AM", 10, false);
	        when(examRepository.findById(examId)).thenReturn(Optional.of(existingExam));
	
	        // Act
	        Exam result = examinerService.declareResult(examId);
	
	        // Assert
	        assertNotNull(result);
	        assertEquals(existingExam, result);
	        assertTrue(result.getResultDeclared());
	        verify(examRepository, times(1)).save(existingExam);
	    }
	
	    @Test
	    public void testDeclareResultWhenExamNotFound() {
	        // Arrange
	        int nonExistingExamId = 999;
	        when(examRepository.findById(nonExistingExamId)).thenReturn(Optional.empty());
	
	        // Act and Assert
	        assertThrows(ResourceNotFoundException.class, () -> examinerService.declareResult(nonExistingExamId));
	        verify(examRepository, never()).save(any());
	    }
	
	    // Add more tests as needed for other methods in ExaminerService
	}