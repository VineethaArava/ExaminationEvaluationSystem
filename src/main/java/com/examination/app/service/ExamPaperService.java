package com.examination.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examination.app.entity.ExamPaper;
import com.examination.app.repository.ExamPaperRepository;


 // Service class for handling operations related to exam papers.
 
@Service
public class ExamPaperService  {

    @Autowired
    ExamPaperRepository examPaperRepository;

    private static final Logger log = LoggerFactory.getLogger(ExamPaperService.class);

    public ExamPaper createTestPaper(ExamPaper examPaper) {
        log.info("SAVING EXAM PAPER");
        examPaperRepository.save(examPaper);
        log.info("EXAM PAPER SAVED");
        return examPaper;
    }

	public void setExamPaperRepository(ExamPaperRepository examPaperRepository) {
		// TODO Auto-generated method stub
		this.examPaperRepository = examPaperRepository;
		
	}
}
