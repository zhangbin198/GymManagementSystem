package com.xhwy.gym.dao.zb;

import com.xhwy.gym.entity.Leavemessage;

import java.util.List;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/7/21 21:40
 */
public interface LeaveMessageDao {
    /**
     * 查询所有数据并分页
     *
     * @param page  第几页
     * @param limit 每页显示多少条数据
     * @return 查询返回的数据集合
     */
    List<Object> allLeaveMessage(String page, String limit);

    /**
     * 模糊查询并分页
     *
     * @param page              第几页
     * @param limit             每页显示多少条数据
     * @param leavemessageId    留言编号
     * @param leavemessageUser  留言姓名
     * @param leavemessagePhone 留言手机
     * @param leavemessageTime  留言时间
     * @return 返回查询留言的对象
     */
    List<Object> allMembersLike(String page, String limit, String leavemessageId, String leavemessageUser, String leavemessagePhone, String leavemessageTime);

    /**
     * 获取留言信息总和
     *
     * @return 返回入账信息总和
     */
    int countLeaveMessage();

    /**
     * 编辑留言信息
     *
     * @param leaveMessage 留言信息对象
     * @return 返回入账信息
     */
    int editLeaveMessage(Leavemessage leaveMessage);

    /**
     * 添加留言信息
     *
     * @param leaveMessage 留言信息对象
     * @return 返回受影响行数
     */
    int addLeaveMessage(Leavemessage leaveMessage);

    /**
     * 根据编号删除指定入账信息
     *
     * @param leaveMessage 入账账号
     * @return 返回受影响行数
     */
    int delLeaveMessage(int leaveMessage);
}
