package com.xhwy.gym.entity;

import java.io.Serializable;

/**
 * 账单实体类
 */
public class Booked implements Serializable {
    private long billId;
    private long billingAccount;
    private String billName;
    private String whetherMembers;
    private double billMoney;
    private String billNotel;
    private String consumerPlace;
    private String billTime;

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public long getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(long billingAccount) {
        this.billingAccount = billingAccount;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getWhetherMembers() {
        return whetherMembers;
    }

    public void setWhetherMembers(String whetherMembers) {
        this.whetherMembers = whetherMembers;
    }

    public double getBillMoney() {
        return billMoney;
    }

    public void setBillMoney(double billMoney) {
        this.billMoney = billMoney;
    }

    public String getBillNotel() {
        return billNotel;
    }

    public void setBillNotel(String billNotel) {
        this.billNotel = billNotel;
    }

    public String getConsumerPlace() {
        return consumerPlace;
    }

    public void setConsumerPlace(String consumerPlace) {
        this.consumerPlace = consumerPlace;
    }
}
