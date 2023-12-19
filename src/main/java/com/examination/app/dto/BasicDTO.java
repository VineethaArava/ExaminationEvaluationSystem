package com.examination.app.dto;

public class BasicDTO<T> {

    // Flag indicating the success status of the operation.
    private boolean isSuccess;

    // A message providing additional information about the operation.
    private String message;

    // Data associated with the response.
    private T data;

    //default constructor
    public BasicDTO() {
		super();
	}
    
    public BasicDTO(T data) {
        this.data = data;
        isSuccess = true;
        message = "Login Successfully";
    }
    //Parameterized constructor

    public BasicDTO(boolean isSuccess, String message, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    // Getter method for retrieving the success status.
    public boolean isSuccess() {
        return isSuccess;
    }

    // Setter method for setting the success status.
    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    // Getter method for retrieving the additional message.
    public String getMessage() {
        return message;
    }

    // Setter method for setting the additional message.
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter method for retrieving the associated data.
    public T getData() {
        return data;
    }

    // Setter method for setting the associated data.
    public void setData(T data) {
        this.data = data;
    }
}
