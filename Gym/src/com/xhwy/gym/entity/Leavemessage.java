package com.xhwy.gym.entity;

import java.io.Serializable;

/**
 * 留言实体类
 */
public class Leavemessage implements Serializable {
    private long leavemessageId;
    private String leavemessageUser;
    private String leavemessageContent;
    private String leavemessagePhone;
    private String leavemessageTime;

    public String getLeavemessageTime() {
        return leavemessageTime;
    }

    public void setLeavemessageTime(String leavemessageTime) {
        this.leavemessageTime = leavemessageTime;
    }

    public long getLeavemessageId() {
        return leavemessageId;
    }

    public void setLeavemessageId(long leavemessageId) {
        this.leavemessageId = leavemessageId;
    }

    public String getLeavemessageUser() {
        return leavemessageUser;
    }

    public void setLeavemessageUser(String leavemessageUser) {
        this.leavemessageUser = leavemessageUser;
    }

    public String getLeavemessageContent() {
        return leavemessageContent;
    }

    public void setLeavemessageContent(String leavemessageContent) {
        this.leavemessageContent = leavemessageContent;
    }

    public String getLeavemessagePhone() {
        return leavemessagePhone;
    }

    public void setLeavemessagePhone(String leavemessagePhone) {
        this.leavemessagePhone = leavemessagePhone;
    }
}
