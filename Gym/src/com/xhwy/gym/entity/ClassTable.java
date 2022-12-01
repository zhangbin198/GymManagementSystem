package com.xhwy.gym.entity;

import java.io.Serializable;

/**
 * 课程实体类
 */
public class ClassTable implements Serializable {
    private int classId;
    private String className;
    private String classSrc;
    private String classBegin;
    private String classTime;
    private String coach;
    private int member_id;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassBegin() {
        return classBegin;
    }

    public void setClassBegin(String classBegin) {
        this.classBegin = classBegin;
    }

    public String getClassTime() {
        return classTime;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getClassSrc() {
        return classSrc;
    }

    public void setClassSrc(String classSrc) {
        this.classSrc = classSrc;
    }
}
