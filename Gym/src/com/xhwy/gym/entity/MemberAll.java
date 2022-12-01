package com.xhwy.gym.entity;

import java.io.Serializable;

/**
 * 会员实体类
 */
public class MemberAll implements Serializable {
    private String memberAccount;
    private String memberPassword;
    private String memberName;
    private String memberGender;
    private int memberAge;
    private int memberHeight;
    private int memberWeight;
    private Long memberPhone;
    private String cardTime;
    private int cardClass;
    private int cardNextClass;
    private String memberId;
    private int classId;
    private String src;
    private int score;
    private String StudentName;
    private String studentSex;
    private String coach;

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public int getMemberAge() {
        return memberAge;
    }

    public void setMemberAge(int memberAge) {
        this.memberAge = memberAge;
    }

    public int getMemberHeight() {
        return memberHeight;
    }

    public void setMemberHeight(int memberHeight) {
        this.memberHeight = memberHeight;
    }

    public int getMemberWeight() {
        return memberWeight;
    }

    public void setMemberWeight(int memberWeight) {
        this.memberWeight = memberWeight;
    }

    public Long getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(Long memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getCardTime() {
        return cardTime;
    }

    public void setCardTime(String cardTime) {
        this.cardTime = cardTime;
    }

    public int getCardClass() {
        return cardClass;
    }

    public void setCardClass(int cardClass) {
        this.cardClass = cardClass;
    }

    public int getCardNextClass() {
        return cardNextClass;
    }

    public void setCardNextClass(int cardNextClass) {
        this.cardNextClass = cardNextClass;
    }

    @Override
    public String toString() {
        return "MemberAll{" +
                "memberAccount='" + memberAccount + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberGender='" + memberGender + '\'' +
                ", memberAge=" + memberAge +
                ", memberHeight=" + memberHeight +
                ", memberWeight=" + memberWeight +
                ", memberPhone=" + memberPhone +
                ", cardTime='" + cardTime + '\'' +
                ", cardClass=" + cardClass +
                ", cardNextClass=" + cardNextClass +
                ", memberId='" + memberId + '\'' +
                ", classId=" + classId +
                '}';
    }
}
