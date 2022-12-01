package com.xhwy.gym.service.lyy.impl;

import com.xhwy.gym.dao.lyy.ClassTableDao;
import com.xhwy.gym.dao.lyy.impl.ClassTableDaoImpl;
import com.xhwy.gym.entity.ClassTable;
import com.xhwy.gym.service.lyy.ClassTableService;

import java.util.List;

/**
 * @author 李缘缘
 * @version 1.0
 * @date 2022/7/21 9:45
 */
public class ClassTableServiceImpl implements ClassTableService {
    //返回课程数据访问层
    ClassTableDao classTables = new ClassTableDaoImpl();

    @Override
    public List<Object> allClassTable(String page, String limit) {
        //查询所有课程信息
        return classTables.allClassTable(page, limit);
    }

    @Override
    public List<Object> allClassTableLike(String page, String limit, String classId, String className, String classBegin, String coach) {
        //返回模糊查询所有课程信息
        return classTables.allClassTableLike(page, limit, classId, className, classBegin, coach);
    }

    @Override
    public int countClassTable() {
        //返回查询所有课程总数
        return classTables.countClassTable();
    }

    @Override
    public int editClassTable(ClassTable classTable) {
        //编辑课程信息
        return classTables.editClassTable(classTable);
    }

    @Override
    public int addClassTable(ClassTable classTable) {
        //添加员工信息
        return classTables.addClassTable(classTable);
    }

    @Override
    public int delClassTable(int classTable) {
        //删除员工信息
        return classTables.delClassTable(classTable);
    }

    @Override
    public int ifHasMember(String memberId) {
        return classTables.ifHasMember(memberId);
    }

    @Override
    public int chooseTitle(int classId, String coach, String memberId) {
        return classTables.chooseTitle(classId, coach, memberId);
    }

    @Override
    public int delTitle(int classId, String coach, String memberId) {
        return classTables.delTitle(classId, coach, memberId);
    }

    @Override
    public String getTName(int classId) {
        return classTables.getTName(classId);
    }
}
