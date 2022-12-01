package com.xhwy.gym.service.zb.impl;

import com.xhwy.gym.dao.zb.BookedDao;
import com.xhwy.gym.dao.zb.impl.BookedDaoImpl;
import com.xhwy.gym.entity.Booked;
import com.xhwy.gym.service.zb.BookedService;

import java.util.List;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/7/21 19:11
 */
public class BookedServiceImpl implements BookedService {
    //创建入账数据访问层
    BookedDao booKedS = new BookedDaoImpl();

    @Override
    public List<Object> allBooked(String page, String limit) {
        //查询所有入账信息
        return booKedS.allBooked(page, limit);
    }

    @Override
    public List<Object> allMembersLike(String page, String limit, String billId, String billingAccount, String billName, String billTime) {
        //模糊查询所有入账信息
        return booKedS.allMembersLike(page, limit, billId, billingAccount, billName, billTime);
    }

    @Override
    public int countBooked() {
        //查询所有入账信息总数
        return booKedS.countBooked();
    }

    @Override
    public int editBooked(Booked booked) {
        //编辑入账信息
        return booKedS.editBooked(booked);
    }

    @Override
    public int addBooked(Booked booked) {
        //添加入账信息
        return booKedS.addBooked(booked);
    }

    @Override
    public int delBooked(int booked) {
        //删除入账信息
        return booKedS.delBooked(booked);
    }
}
