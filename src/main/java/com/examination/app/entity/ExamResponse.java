package com.examination.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class ExamResponse {

    // Unique identifier for the exam response.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Reference to the associated student.
    @OneToOne
    private Student student;

    // Reference to the associated exam paper (question).
    @OneToOne
    private ExamPaper examPaper;

    // Selected choice made by the student for the question.
    @NotNull(message="selected choice should not be null")
    @NotBlank(message = "selectedChoice is mandatory")
    private String selectedChoice;

    // Indicates whether the selected choice is correct or not.
    @NotNull(message="it  should not be null")
    @NotBlank(message = "isCorrect is mandatory")
    private Boolean isCorrect;

    

    public ExamResponse() {
		super();
	}

	
    public ExamResponse(Integer id, Student student, ExamPaper examPaper, String selectedChoice, Boolean isCorrect) {
        this.id = id;
        this.student = student;
        this.examPaper = examPaper;
        this.selectedChoice = selectedChoice;
        this.isCorrect = isCorrect;
    }
 // Getter and Setter methods for each property.

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public ExamPaper getExamPaper() {
		return examPaper;
	}

	public void setExamPaper(ExamPaper examPaper) {
		this.examPaper = examPaper;
	}

	public String getSelectedChoice() {
		return selectedChoice;
	}

	public void setSelectedChoice(String selectedChoice) {
		this.selectedChoice = selectedChoice;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

    


}
