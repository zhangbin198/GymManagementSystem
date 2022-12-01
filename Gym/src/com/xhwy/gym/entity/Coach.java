package com.xhwy.gym.entity;

import java.io.Serializable;

public class Coach implements Serializable {
    private String coachId;
    private String coachPassword;
    private String coachName;
    private String coachGender;
    private String coachAge;
    private String coachField;
    private String coachSaff;

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getCoachPassword() {
        return coachPassword;
    }

    public void setCoachPassword(String coachPassword) {
        this.coachPassword = coachPassword;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachGender() {
        return coachGender;
    }

    public void setCoachGender(String coachGender) {
        this.coachGender = coachGender;
    }

    public String getCoachAge() {
        return coachAge;
    }

    public void setCoachAge(String coachAge) {
        this.coachAge = coachAge;
    }

    public String getCoachField() {
        return coachField;
    }

    public void setCoachField(String coachField) {
        this.coachField = coachField;
    }

    public String getCoachSaff() {
        return coachSaff;
    }

    public void setCoachSaff(String coachSaff) {
        this.coachSaff = coachSaff;
    }
}
