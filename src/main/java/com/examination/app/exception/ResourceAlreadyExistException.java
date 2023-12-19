package com.examination.app.exception;
public class ResourceAlreadyExistException extends RuntimeException {

    
     // Default constructor with a predefined error message.
     
    public ResourceAlreadyExistException() {
        super("Resource already exists in the system.");
    }

    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
