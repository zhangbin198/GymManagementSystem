package com.xhwy.gym.entity;

import java.io.Serializable;

/**
 * 员工实体类
 */
public class Employee implements Serializable {
    private long employeeAccount;
    private String employeeName;
    private String employeeGender;
    private long employeeAge;
    private String entryTime;
    private String staff;
    private String employeeMessage;

    public long getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(long employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public long getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(long employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getEmployeeMessage() {
        return employeeMessage;
    }

    public void setEmployeeMessage(String employeeMessage) {
        this.employeeMessage = employeeMessage;
    }
}
