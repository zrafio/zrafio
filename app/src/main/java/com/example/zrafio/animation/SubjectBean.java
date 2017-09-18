package com.example.zrafio.animation;

/**
 * Created by zrafio on 9/4/17.
 */

public class SubjectBean {

    private long aid;
    private String bfirstName,clastName,dgender,ebirthday,registrationDate;

    public SubjectBean(){
        super();
    }

    public SubjectBean(long aid, String bfirstName, String clastName,String dgender,
                       String ebirthday,  String registrationDate) {
        this.aid = aid;
        this.bfirstName = bfirstName;
        this.clastName = clastName;
        this.ebirthday = ebirthday;
        this.dgender = dgender;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return aid;
    }

    public void setId(long aid) {
        this.aid = aid;
    }

    public String getFirstName() {
        return bfirstName;
    }

    public void setFirstName(String bfirstName) {
        this.bfirstName = bfirstName;
    }

    public String getLastName() {
        return clastName;
    }

    public void setLastName(String clastName) {
        this.clastName = clastName;
    }

    public String getGender() {
        return dgender;
    }

    public void setGender(String dgender) {
        this.dgender = dgender;
    }

    public String getBirthday() {
        return ebirthday;
    }

    public void setBirthday(String ebirthday) {
        this.ebirthday = ebirthday;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
