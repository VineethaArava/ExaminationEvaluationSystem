package com.examination.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;


@Entity
@Validated
public class Exam {

    // Unique identifier for the exam.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Name of the exam.
    @NotNull(message="Name should not be null")
    //@NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message="duration should not be null")
    private Float duration;

    @NotNull(message="start time should not be null")
    private String startTime;
    
    @NotNull(message="noOfQuestion should not be null")
    private Integer noOfQuestion;

    // Flag indicating whether the exam result has been declared or not.
    @NotNull(message="result declared should not be null")
    @Column(columnDefinition = "boolean default false")
    private Boolean resultDeclared;

    
    

    public Exam() {
		super();
	}

	
    public Exam(Integer id, String name, Float duration, String startTime, Integer noOfQuestion, Boolean resultDeclared) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.startTime = startTime;
        this.noOfQuestion = noOfQuestion;
        this.resultDeclared = resultDeclared;
    }

    // Getter and Setter methods for each property.

   
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    
    public String getStartTime() {
        return startTime;
    }

    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    
    public Integer getNoOfQuestion() {
        return noOfQuestion;
    }

    
    public void setNoOfQuestion(Integer noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    
    public Boolean getResultDeclared() {
        return resultDeclared;
    }

    public void setResultDeclared(Boolean resultDeclared) {
        this.resultDeclared = resultDeclared;
    }
}
