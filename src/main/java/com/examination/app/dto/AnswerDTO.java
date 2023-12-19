package com.examination.app.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

// Data Transfer Object (DTO) representing answers submitted by a student for an exam.
public class AnswerDTO {

    // ID of the exam for which answers are being submitted.
    @NotBlank
    private Integer examId;

    // ID of the student submitting the answers.
    @NotBlank
    private Integer studentId;

    // List of answer options chosen by the student.
    private List<AnswerOptionDTO> answers;

    

    public AnswerDTO() {
		super();
	}

	// Parameterized constructor to initialize the AnswerDTO with exam ID, student ID, and answers.
    public AnswerDTO(Integer examId, Integer studentId, List<AnswerOptionDTO> answers) {
        this.examId = examId;
        this.studentId = studentId;
        this.answers = answers;
    }

    // Getter method for retrieving the exam ID.
    public Integer getExamId() {
        return examId;
    }

    // Setter method for setting the exam ID.
    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    // Getter method for retrieving the student ID.
    public Integer getStudentId() {
        return studentId;
    }

    // Setter method for setting the student ID.
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    // Getter method for retrieving the list of answers.
    public List<AnswerOptionDTO> getAnswers() {
        return answers;
    }

    // Setter method for setting the list of answers.
    public void setAnswers(List<AnswerOptionDTO> answers) {
        this.answers = answers;
    }
}
