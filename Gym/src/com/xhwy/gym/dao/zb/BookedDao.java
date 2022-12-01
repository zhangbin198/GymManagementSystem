package com.xhwy.gym.dao.zb;

import com.xhwy.gym.entity.Booked;

import java.util.List;

/**
 * 查询所有入账信息
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/21 19:00
 */
public interface BookedDao {
    /**
     * 查询所有数据并分页
     *
     * @param page  第几页
     * @param limit 每页显示多少条数据
     * @return 查询返回的数据集合
     */
    List<Object> allBooked(String page, String limit);

    /**
     * 模糊查询并分页
     *
     * @param page           第几页
     * @param limit          每页显示多少条数据
     * @param billId         账编号
     * @param billingAccount 用户账号
     * @param billName       用户姓名
     * @param billTime       入账日期
     * @return 返回查询的对象
     */
    List<Object> allMembersLike(String page, String limit, String billId, String billingAccount, String billName, String billTime);

    /**
     * 获取入账信息总和
     *
     * @return 返回入账信息总和
     */
    int countBooked();

    /**
     * 编辑入账信息
     *
     * @param booked 入账信息对象
     * @return 返回入账信息
     */
    int editBooked(Booked booked);

    /**
     * 添加入账信息
     *
     * @param booked 入账信息对象
     * @return 返回受影响行数
     */
    int addBooked(Booked booked);

    /**
     * 根据编号删除指定入账信息
     *
     * @param booked 入账账号
     * @return 返回受影响行数
     */
    int delBooked(int booked);
}
