package com.examination.app.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.examination.app.entity.ExamPaper;
import com.examination.app.repository.ExamPaperRepository;
import com.examination.app.service.ExamPaperService;

public class ExamPaperServiceTest {

    @Test
    public void testCreateTestPaper() {
        // Mock the ExamPaperRepository
        ExamPaperRepository examPaperRepository = mock(ExamPaperRepository.class);

        // Create an instance of ExamPaperService and set the mocked repository
        ExamPaperService examPaperService = new ExamPaperService();
        examPaperService.setExamPaperRepository(examPaperRepository);

        // Create a sample ExamPaper
        ExamPaper examPaper = new ExamPaper();
        examPaper.setQuestion("Sample Question");
        // Set other properties as needed

        // Test the createTestPaper method
        examPaperService.createTestPaper(examPaper);

        // Verify that the save method of ExamPaperRepository was called with the correct argument
        verify(examPaperRepository, times(1)).save(examPaper);
    }
}

