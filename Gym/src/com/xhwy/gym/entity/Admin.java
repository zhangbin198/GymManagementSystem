package com.xhwy.gym.entity;

import java.io.Serializable;

/**
 * 管理员实体类
 */
public class Admin implements Serializable {
    private long adminAccount;
    private String adminPassword;
    private String adminId;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public long getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(long adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
