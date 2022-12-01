package com.xhwy.gym.service.lyy;

import com.xhwy.gym.entity.ClassTable;

import java.util.List;

/**
 * @author 李缘缘
 * @version 1.0
 * @date 2022/7/21 9:44
 */
public interface ClassTableService {
    /**
     * 查询所有数据并分页
     *
     * @param page  第几页
     * @param limit 每页显示多少条数据
     * @return 查询返回的数据集合
     */
    List<Object> allClassTable(String page, String limit);

    /**
     * 模糊查询器材信息
     *
     * @param page       第几页
     * @param limit      每页显示多少条数据
     * @param classId    员课程编号
     * @param className  课程名称
     * @param classBegin 开课时间
     * @param coach      教练
     * @return 返回模糊查询到的员工信息
     */
    List<Object> allClassTableLike(String page, String limit, String classId, String className, String classBegin, String coach);

    /**
     * 获取课程信息总和
     *
     * @return 返回课程信息总和
     */
    int countClassTable();

    /**
     * 编辑课程信息
     *
     * @param classTable 课程信息对象
     * @return 返回课程信息
     */
    int editClassTable(ClassTable classTable);

    /**
     * 添加课程信息
     *
     * @param classTable 课程信息对象
     * @return 返回受影响行数
     */
    int addClassTable(ClassTable classTable);

    /**
     * 根据编号删除指定课程信息
     *
     * @param classTable 课程对象
     * @return 返回受影响行数
     */
    int delClassTable(int classTable);

    /**
     * 查询选题是否被选
     *
     * @param memberId
     * @return
     */
    int ifHasMember(String memberId);

    /**
     * 如果们没有被选就选择课程
     *
     * @param memberId
     * @param coach
     * @param classId
     * @return
     */
    int chooseTitle(int classId, String coach, String memberId);

    /**
     * 点击退选,退选当前选题
     *
     * @param classId  课程编号
     * @param coach    教练编号
     * @param memberId 会员编号
     * @return
     */
    int delTitle(int classId, String coach, String memberId);

    /**
     * 根据课程编号查询课程名称
     *
     * @param classId
     * @return
     */
    public String getTName(int classId);
}
