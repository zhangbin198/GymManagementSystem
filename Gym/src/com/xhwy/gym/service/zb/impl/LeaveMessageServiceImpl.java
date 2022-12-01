package com.xhwy.gym.service.zb.impl;

import com.xhwy.gym.dao.zb.LeaveMessageDao;
import com.xhwy.gym.dao.zb.impl.LeaveMessageDaoImpl;
import com.xhwy.gym.entity.Leavemessage;
import com.xhwy.gym.service.zb.LeaveMessageService;

import java.util.List;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/7/21 21:53
 */
public class LeaveMessageServiceImpl implements LeaveMessageService {
    //创建留言数据访问层
    LeaveMessageDao leaveMessages = new LeaveMessageDaoImpl();

    @Override
    public List<Object> allLeaveMessage(String page, String limit) {
        //返回查询所有留言信息
        return leaveMessages.allLeaveMessage(page, limit);
    }

    @Override
    public List<Object> allMembersLike(String page, String limit, String leavemessageId, String leavemessageUser, String leavemessagePhone, String leavemessageTime) {
        //返回模糊查询所有留言信息
        return leaveMessages.allMembersLike(page, limit, leavemessageId, leavemessageUser, leavemessagePhone, leavemessageTime);
    }

    @Override
    public int countLeaveMessage() {
        //返回查询所有留言信息总数
        return leaveMessages.countLeaveMessage();
    }

    @Override
    public int editLeaveMessage(Leavemessage leaveMessage) {
        //返回编辑留言信息
        return leaveMessages.editLeaveMessage(leaveMessage);
    }

    @Override
    public int addLeaveMessage(Leavemessage leaveMessage) {
        //返回添加留言信息
        return leaveMessages.addLeaveMessage(leaveMessage);
    }

    @Override
    public int delLeaveMessage(int leaveMessage) {
        //返回删除留言信息
        return leaveMessages.delLeaveMessage(leaveMessage);
    }
}
