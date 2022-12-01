package com.xhwy.gym.entity;

import java.io.Serializable;

/**
 * 器材实体类
 */
public class Equipment implements Serializable {
    private long equipmentId;
    private String equipmentName;
    private String equipmentLocation;
    private String equipmentStatus;
    private String equipmentMessage;

    public long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentLocation() {
        return equipmentLocation;
    }

    public void setEquipmentLocation(String equipmentLocation) {
        this.equipmentLocation = equipmentLocation;
    }

    public String getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public String getEquipmentMessage() {
        return equipmentMessage;
    }

    public void setEquipmentMessage(String equipmentMessage) {
        this.equipmentMessage = equipmentMessage;
    }

}
