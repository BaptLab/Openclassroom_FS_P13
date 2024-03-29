package com.openclassrooms.Openclassrooms_FS_P13_POC.DTOs;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {
    
    @NotNull
    @Email
    private String email;
    
    @NotNull
    @Size(min = 1, max = 255)
    private String username;

    // Add getters and setters for new fields (lastName, firstName, birthDate)
    private String lastName;
    private String firstName;
    private Date birthDate;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

