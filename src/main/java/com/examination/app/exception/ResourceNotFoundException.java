package com.examination.app.exception;


 // Exception thrown when a requested resource is not found in the system.
 
public class ResourceNotFoundException extends RuntimeException {

    
    public ResourceNotFoundException() {
        super("Current Resource not found.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
