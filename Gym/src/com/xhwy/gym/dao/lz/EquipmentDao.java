package com.xhwy.gym.dao.lz;

import com.xhwy.gym.entity.Equipment;

import java.util.List;

/**
 * 查询所有器材
 *
 * @author 廖振
 * @version 1.0
 * @date 2022/7/20 22:43
 */
public interface EquipmentDao {
    /**
     * 查询所有数据并分页
     *
     * @param page  第几页
     * @param limit 每页显示多少条数据
     * @return 查询返回的数据集合
     */
    List<Object> allEquipment(String page, String limit);

    /**
     * 模糊查询器材信息
     *
     * @param page              第几页
     * @param limit             每页显示多少条数据
     * @param equipmentId       器材编号
     * @param equipmentName     器材名称
     * @param equipmentLocation 器材位置
     * @param equipmentStatus   器材状态
     * @return 返回模糊查询到的器材信息
     */
    List<Object> allEquipmentLike(String page, String limit, String equipmentId, String equipmentName, String equipmentLocation, String equipmentStatus);

    /**
     * 获取器材信息总和
     *
     * @return 返回器材信息总和
     */
    int countEquipment();

    /**
     * 编辑器材信息
     *
     * @param equipment 器材信息对象
     * @return 返回器材信息
     */
    int editEquipment(Equipment equipment);

    /**
     * 添加器材信息
     *
     * @param equipment 器材信息对象
     * @return 返回受影响行数
     */
    int addEquipment(Equipment equipment);

    /**
     * 根据编号删除指定器材信息
     *
     * @param equipment 器材账号
     * @return 返回受影响行数
     */
    int delEquipment(int equipment);
}
