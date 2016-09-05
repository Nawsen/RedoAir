package com.realdolmen.VO;

/**
 * Created by WVDAZ49 on 5/09/2016.
 */
public class CustomerRegisterVO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public CustomerRegisterVO() {
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
