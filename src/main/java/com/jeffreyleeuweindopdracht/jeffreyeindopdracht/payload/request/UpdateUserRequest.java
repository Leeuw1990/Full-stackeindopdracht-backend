package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UpdateUserRequest {

    @Size(max = 50)
    @Email
    private String email;

    @Size(min = 8, max = 40)
    private String password;

    private String passwordConfirmation;

    private String firstName;

    private String lastName;

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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
