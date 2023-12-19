package com.examination.app.dto;

import com.examination.app.entity.User_App;
import com.examination.app.util.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;


public class UserDTO {

    // Unique identifier for the user.
    private Integer id;

    // User's name.
    @NotBlank
    private String name;

    // User's email address.
    @NotBlank
    private String email;

    // User's password (JsonIgnore is used to exclude it from serialization).
    @NotBlank
   
    private String password;

    // User's role (e.g., ADMIN, STUDENT).
    @NotBlank
    private UserRoleEnum role;

    
   
    public static UserDTO toDTO(User_App user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), null, user.getRole());
    }

    
   
    public User_App toObject() {
        return new User_App(id, name, email, password, role);
    }

    

    public UserDTO() {
		super();
	}



    public UserDTO(Integer id, String name, String email, String password, UserRoleEnum role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
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

    
    public UserRoleEnum getRole() {
        return role;
    }

    
    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
