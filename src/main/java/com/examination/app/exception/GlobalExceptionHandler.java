package com.examination.app.exception;

import com.examination.app.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


 //GlobalExceptionHandler class handles exceptions thrown during request processing
 // and provides appropriate ResponseEntity with error details.
 
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    
     //Exception handler for BadCredentialsException.
     
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    protected ResponseEntity<ErrorDTO> handleItemAlreadyExistException(ResourceAlreadyExistException ex) {
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.CONFLICT);
    }

  

}
