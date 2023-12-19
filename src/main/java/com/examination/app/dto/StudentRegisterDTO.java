package com.examination.app.dto;

import com.examination.app.util.UserRoleEnum;

import javax.validation.constraints.NotBlank;


public class StudentRegisterDTO extends UserDTO {

    // Additional properties specific to student registration.
    private String address;
    private String contact;

    
    public StudentRegisterDTO(Integer id, @NotBlank String name, @NotBlank String email, @NotBlank String password,
                              @NotBlank UserRoleEnum role, String address, String contact) {
        super(id, name, email, password, role);
        this.address = address;
        this.contact = contact;
    }

    
    public StudentRegisterDTO(String address, String contact) {
        this.address = address;
        this.contact = contact;
    }
    


    public StudentRegisterDTO() {
		super();
	}


	
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    public String getContact() {
        return contact;
    }

    
    public void setContact(String contact) {
        this.contact = contact;
    }
}
