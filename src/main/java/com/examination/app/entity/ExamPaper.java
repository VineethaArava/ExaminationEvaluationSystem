package com.examination.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;


@Entity
@Validated
public class ExamPaper {

    // Unique identifier for the exam paper.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Reference to the associated exam.
    @OneToOne
    private Exam exam;

    @NotBlank(message = "question is mandatory")
    private String question;

    @NotBlank(message = "choiceOne is mandatory")
    private String choiceOne;
   
    @NotBlank(message = "choiceTwo is mandatory")
    private String choiceTwo;

    @NotBlank(message = "choiceThree is mandatory")
    private String choiceThree;

   
    @NotBlank(message = "choiceFour is mandatory")
    private String choiceFour;
    
    @NotBlank(message = "correctOption is mandatory")
    private String correctOption;

    
    @NotNull(message="mark should not be null")
    private Integer mark;

    

    public ExamPaper() {
		super();
	}

	
    public ExamPaper(Integer id, Exam exam, String question, String choiceOne, String choiceTwo,
                     String choiceThree, String choiceFour, String correctOption, Integer mark) {
        this.id = id;
        this.exam = exam;
        this.question = question;
        this.choiceOne = choiceOne;
        this.choiceTwo = choiceTwo;
        this.choiceThree = choiceThree;
        this.choiceFour = choiceFour;
        this.correctOption = correctOption;
        this.mark = mark;
    }
    
 // Getter and Setter methods for each property.

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChoiceOne() {
		return choiceOne;
	}

	public void setChoiceOne(String choiceOne) {
		this.choiceOne = choiceOne;
	}

	public String getChoiceTwo() {
		return choiceTwo;
	}

	public void setChoiceTwo(String choiceTwo) {
		this.choiceTwo = choiceTwo;
	}

	public String getChoiceThree() {
		return choiceThree;
	}

	public void setChoiceThree(String choiceThree) {
		this.choiceThree = choiceThree;
	}

	public String getChoiceFour() {
		return choiceFour;
	}

	public void setChoiceFour(String choiceFour) {
		this.choiceFour = choiceFour;
	}

	public String getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

    


}
