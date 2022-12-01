package com.xhwy.gym.entity;

import java.io.Serializable;

/**
 * 报名实体类
 */
public class ClassOrder implements Serializable {
    private long classOrderId;
    private String className;
    private String classPhone;
    private String classTable;
    private String classCoach;
    private String classTime;

    public long getClassOrderId() {
        return classOrderId;
    }

    public void setClassOrderId(long classOrderId) {
        this.classOrderId = classOrderId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassPhone() {
        return classPhone;
    }

    public void setClassPhone(String classPhone) {
        this.classPhone = classPhone;
    }

    public String getClassTable() {
        return classTable;
    }

    public void setClassTable(String classTable) {
        this.classTable = classTable;
    }

    public String getClassCoach() {
        return classCoach;
    }

    public void setClassCoach(String classCoach) {
        this.classCoach = classCoach;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }
}
