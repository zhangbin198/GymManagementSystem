package com.xhwy.gym.dao.zb;

import com.xhwy.gym.entity.Coach;

import java.util.List;

/**
 * 教练表数据访问层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/8/1 19:38
 */
public interface CoachDao {
    /**
     * 查询所有数据并分页
     *
     * @param page  第几页
     * @param limit 每页显示多少条数据
     * @return 查询返回的数据集合
     */
    List<Object> allCoach(String page, String limit);

    /**
     * 模糊查询教练信息
     *
     * @param page       第几页
     * @param limit      每页显示多少条数据
     * @param coachId    教练编号
     * @param coachName  教练名称
     * @param coachSeff  教练阶级
     * @param coachField 教练领域
     * @return 返回模糊查询到的员工信息
     */
    List<Object> allCoachLike(String page, String limit, String coachId, String coachName, String coachField, String coachSeff);

    /**
     * 获取教练信息总和
     *
     * @return 返回课程信息总和
     */
    int countCoach();

    /**
     * 编辑教练信息
     *
     * @param coach 教练信息对象
     * @return 返回教练信息
     */
    int editCoach(Coach coach);

    /**
     * 添加教练信息
     *
     * @param coach 教练信息对象
     * @return 返回受影响行数
     */
    int addCoach(Coach coach);

    /**
     * 根据编号删除指定教练信息
     *
     * @param coach 教练对象
     * @return 返回受影响行数
     */
    int delCoach(int coach);
}
