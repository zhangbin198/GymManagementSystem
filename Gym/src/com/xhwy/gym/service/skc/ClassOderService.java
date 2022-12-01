package com.xhwy.gym.service.skc;

import com.xhwy.gym.entity.ClassOrder;

import java.util.List;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/7/21 7:27
 */
public interface ClassOderService {
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
     * @param page         第几页
     * @param limit        每页显示多少条数据
     * @param classOrderId 报名编号
     * @param className    报名名称
     * @param classCoach   报名教练
     * @param class_Phone  报名电话
     * @return 返回模糊查询到的报名信息
     */
    List<Object> allClassOrderLike(String page, String limit, String classOrderId, String className, String classCoach, String class_Phone);

    /**
     * 获取报名信息总和
     *
     * @return 返回报名信息总和
     */
    int countClassOrder();

    /**
     * 编辑器材信息
     *
     * @param classOrder 器材信息对象
     * @return 返回器材信息
     */
    int editClassOrder(ClassOrder classOrder);

    /**
     * 添加器材信息
     *
     * @param classOrder 器材信息对象
     * @return 返回受影响行数
     */
    int addClassOrder(ClassOrder classOrder);

    /**
     * 根据编号删除指定器材信息
     *
     * @param classOrder 器材账号
     * @return 返回受影响行数
     */
    int delClassOrder(int classOrder);
}
