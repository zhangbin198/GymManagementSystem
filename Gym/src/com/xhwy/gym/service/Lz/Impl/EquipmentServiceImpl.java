package com.xhwy.gym.service.Lz.Impl;

import com.xhwy.gym.dao.lz.EquipmentDao;
import com.xhwy.gym.dao.lz.impl.EquipmentDaoImpl;
import com.xhwy.gym.entity.Equipment;
import com.xhwy.gym.service.Lz.EquipmentService;

import java.util.List;

/**
 * @author 廖振
 * @version 1.0
 * @date 2022/7/20 22:55
 */
public class EquipmentServiceImpl implements EquipmentService {
    //创建器材数据访问层
    EquipmentDao equipments = new EquipmentDaoImpl();

    @Override
    public List<Object> allEquipment(String page, String limit) {
        //返回查询所有器材信息
        return equipments.allEquipment(page, limit);
    }

    @Override
    public List<Object> allEquipmentLike(String page, String limit, String equipmentId, String equipmentName, String equipmentLocation, String equipmentStatus) {
        //返回模糊查询所有器材信息
        return equipments.allEquipmentLike(page, limit, equipmentId, equipmentName, equipmentLocation, equipmentStatus);
    }

    @Override
    public int countEquipment() {
        //查询所有器材信息总数
        return equipments.countEquipment();
    }

    @Override
    public int editEquipment(Equipment equipment) {
        //编辑器材信息
        return equipments.editEquipment(equipment);
    }

    @Override
    public int addEquipment(Equipment equipment) {
        //添加器材信息
        return equipments.addEquipment(equipment);
    }

    @Override
    public int delEquipment(int equipment) {
        //删除器材信息
        return equipments.delEquipment(equipment);
    }
}
