package com.examination.app.dto;

import javax.validation.constraints.NotBlank;

// Data Transfer Object (DTO) representing an answer option selected by a student for a specific question.
public class AnswerOptionDTO {

    // ID of the question for which an answer option is selected.
    @NotBlank
    private Integer questionId;

    // The selected answer option chosen by the student.
    @NotBlank
    private String selectedAnswer;

    // Default constructor.
    public AnswerOptionDTO() {
    }

    // Parameterized constructor to initialize the AnswerOptionDTO with question ID and selected answer.
    public AnswerOptionDTO(Integer questionId, String selectedAnswer) {
        this.questionId = questionId;
        this.selectedAnswer = selectedAnswer;
    }

    // Getter method for retrieving the question ID.
    public Integer getQuestionId() {
        return questionId;
    }

    // Setter method for setting the question ID.
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    // Getter method for retrieving the selected answer.
    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    // Setter method for setting the selected answer.
    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
