package com.examination.app.dto;

import com.examination.app.entity.User_App;


//  A Data Transfer Object (DTO) representing the response after a successful user login.
 
public class LoginResponseDTO {

    // The authentication token generated for the logged-in user.
    private String token;

    // The User entity representing details about the logged-in user.
    private User_App user;

    

    public LoginResponseDTO() {
		super();
	}

	
    public LoginResponseDTO(String token, User_App user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    
    public void setToken(String token) {
        this.token = token;
    }

    
    public User_App getUser() {
        return user;
    }

   
    public void setUser(User_App user) {
        this.user = user;
    }
}
