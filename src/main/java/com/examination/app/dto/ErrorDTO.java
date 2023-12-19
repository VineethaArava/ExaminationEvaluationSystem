package com.examination.app.dto;


 // A Data Transfer Object (DTO) representing an error response with success status and an error message.
 
public class ErrorDTO {

    // Flag indicating the success status of the operation.
    private boolean isSuccess;

    // A message providing information about the error.
    private String message;

   
    public ErrorDTO(String message) {
        this.message = message;
    }

    
    public ErrorDTO(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
    


    public ErrorDTO() {
		super();
	}


    public boolean isSuccess() {
        return isSuccess;
    }

    
    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
