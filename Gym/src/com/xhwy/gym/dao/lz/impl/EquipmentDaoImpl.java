package com.xhwy.gym.dao.lz.impl;

import com.xhwy.gym.dao.lz.EquipmentDao;
import com.xhwy.gym.entity.Equipment;
import com.xhwy.gym.util.JdbcUtils;
import com.xhwy.gym.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 廖振
 * @version 1.0
 * @date 2022/7/20 22:44
 */
public class EquipmentDaoImpl extends JdbcUtils implements EquipmentDao {
    @Override
    public List<Object> allEquipment(String page, String limit) {
        //返回对象
        List<Object> equipments = new ArrayList<>();
        //连接对象
        Connection conn = getConnection();
        //sql
        String sql = " select * from equipment order by equipment_id desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                Equipment equipment = new Equipment();
                equipment.setEquipmentId(rs.getInt(1));
                equipment.setEquipmentName(rs.getString(2));
                equipment.setEquipmentLocation(rs.getString(3));
                equipment.setEquipmentStatus(rs.getString(4));
                equipment.setEquipmentMessage(rs.getString(5));
                equipments.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return equipments;
    }

    @Override
    public List<Object> allEquipmentLike(String page, String limit, String equipmentId, String equipmentName, String equipmentLocation, String equipmentStatus) {
        //返回对象
        List<Object> equipments = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from equipment " +
                " where equipment_id like '%" + equipmentId + "%' and equipment_name like '%" + equipmentName + "%' and equipment_location like '%" + equipmentLocation + "%' and equipment_status like '%" + equipmentStatus + "%'" +
                " order by equipment_id desc limit ?,?";
        //page,limit 转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                Equipment equipment = new Equipment();
                equipment.setEquipmentId(rs.getInt(1));
                equipment.setEquipmentName(rs.getString(2));
                equipment.setEquipmentLocation(rs.getString(3));
                equipment.setEquipmentStatus(rs.getString(4));
                equipment.setEquipmentMessage(rs.getString(5));
                equipments.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return equipments;
    }

    @Override
    public int countEquipment() {
        //返回对象
        int cnt = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "select count(*) from equipment";
        //返回结果集对象
        ResultSet rs = null;
        try {
            //解析
            rs = Utils.getResultSet(sql);
            if (rs.next()) {
                cnt = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回对象
        return cnt;
    }

    @Override
    public int editEquipment(Equipment equipment) {
        //返回对象
        int i = 0;
        //连接对象
        Connection conn = getConnection();
        //sql
        String sql = "update equipment set equipment_name=?,equipment_location=?,equipment_status=?,equipment_message=? " +
                " where equipment_id=?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, equipment.getEquipmentName());
            stat.setObject(2, equipment.getEquipmentLocation());
            stat.setObject(3, equipment.getEquipmentStatus());
            stat.setObject(4, equipment.getEquipmentMessage());
            stat.setObject(5, equipment.getEquipmentId());
            //受影响行数
            i = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(null, stat, conn);
        }
        //返回对象
        return i;
    }

    @Override
    public int addEquipment(Equipment equipment) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "insert into equipment(equipment_name,equipment_location,equipment_status,equipment_message)values(?,?,?,?)";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, equipment.getEquipmentName());
            stat.setObject(2, equipment.getEquipmentLocation());
            stat.setObject(3, equipment.getEquipmentStatus());
            stat.setObject(4, equipment.getEquipmentMessage());
            //返回受影响行数
            i = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(null, stat, conn);
        }
        //返回对象
        return i;
    }

    @Override
    public int delEquipment(int equipment) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "delete from equipment where equipment_id = ?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, equipment);
            //返回受影响行数
            i = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(null, stat, conn);
        }
        //返回对象
        return i;
    }
}
