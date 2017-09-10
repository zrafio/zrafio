package com.example.zrafio.animation;

/**
 * Created by zrafio on 9/4/17.
 */

public class SubjectBean {

    private long id;
    private String firstName,lastName,gender,birthday,registrationString;

    public SubjectBean(long id, String firstName, String lastName,String gender,
                       String birthday,  String registrationString) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.registrationString = registrationString;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegistrationString() {
        return registrationString;
    }

    public void setRegistrationString(String registrationString) {
        this.registrationString = registrationString;
    }
}
