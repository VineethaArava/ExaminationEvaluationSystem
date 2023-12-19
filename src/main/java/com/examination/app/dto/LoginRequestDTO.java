package com.examination.app.dto;

import javax.validation.constraints.NotBlank;


 // A Data Transfer Object (DTO) representing the request for user login.
 
public class LoginRequestDTO {

    // The email address associated with the user's account.
    @NotBlank
    private String email;

    // The password provided by the user for authentication.
    @NotBlank
    private String password;

   

    public LoginRequestDTO() {
		super();
	}

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    
     // Getter&Setter methods for each property
     
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }
}
