package com.realdolmen.domain;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Embeddable
public class Person {
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Person() {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
