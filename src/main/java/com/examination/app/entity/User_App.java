package com.examination.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.examination.app.util.UserRoleEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class User_App {

    // Unique identifier for the user.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Name of the user.
    @NotNull(message="name should not be null")
  //  @NotBlank(message = "name is mandatory")
    private String name;

    // Email address of the user.
    @NotNull(message="email should not be null")
   // @NotBlank(message = "email is mandatory")
    private String email;

    
    @NotNull(message="password should not be null")
    private String password;

    // Role of the user defined by UserRoleEnum.
    @NotNull(message="role should not be null")
    //@NotBlank(message = "role is mandatory")
    private UserRoleEnum role;

    

    public User_App() {
		super();
	}

	
    public User_App(Integer id, String name, String email, String password, UserRoleEnum role) {
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
