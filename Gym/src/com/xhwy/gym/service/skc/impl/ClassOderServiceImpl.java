package com.xhwy.gym.service.skc.impl;

import com.xhwy.gym.dao.skc.ClassOrderDao;
import com.xhwy.gym.dao.skc.Impl.ClassOrderDaoImpl;
import com.xhwy.gym.entity.ClassOrder;
import com.xhwy.gym.service.skc.ClassOderService;

import java.util.List;

/**
 * @author 佘柯澂
 * @version 1.0
 * @date 2022/7/21 7:27
 */
public class ClassOderServiceImpl implements ClassOderService {
    //创建报名信息数据访问层
    ClassOrderDao classOrders = new ClassOrderDaoImpl();

    @Override
    public List<Object> allEquipment(String page, String limit) {
        //查询所有报名信息
        return classOrders.allEquipment(page, limit);
    }

    @Override
    public List<Object> allClassOrderLike(String page, String limit, String classOrderId, String className, String classCoach, String class_Phone) {
        //模糊查询所有报名信息
        return classOrders.allClassOrderLike(page, limit, classOrderId, className, classCoach, class_Phone);
    }

    @Override
    public int countClassOrder() {
        //查询所有报名信息总数
        return classOrders.countClassOrder();
    }

    @Override
    public int editClassOrder(ClassOrder classOrder) {
        //编辑报名信息
        return classOrders.editClassOrder(classOrder);
    }

    @Override
    public int addClassOrder(ClassOrder classOrder) {
        //添加报名信息
        return classOrders.addClassOrder(classOrder);
    }

    @Override
    public int delClassOrder(int classOrder) {
        //删除报名信息
        return classOrders.delClassOrder(classOrder);
    }
}
