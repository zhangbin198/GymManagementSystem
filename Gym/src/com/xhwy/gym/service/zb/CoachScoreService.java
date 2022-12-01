package com.xhwy.gym.service.zb;

import com.xhwy.gym.entity.CoachScore;

import java.util.List;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/8/3 13:50
 */
public interface CoachScoreService {
    /**
     * 查询所有数据并分页
     *
     * @param page  第几页
     * @param limit 每页显示多少条数据
     * @return 查询返回的数据集合
     */
    List<Object> allCoach(String page, String limit, String coachId);

    /**
     * 模糊查询学员信息
     *
     * @param page         第几页
     * @param limit        每页显示多少条数据
     * @param studentId    学员编号
     * @param studentName  学员名称
     * @param coachId      学员选择课程编号
     * @param studentScore 学员成绩
     * @return 返回模糊查询到的学员信息
     */
    List<Object> allCoachScore(String page, String limit, String studentId, String studentName, String coachId, String studentScore);

    /**
     * 获取学员信息总和
     *
     * @return 返回学员信息总和
     */
    int countCoachScore(String coachId);

    /**
     * 编辑教练信息
     *
     * @param coachScore 教练信息对象
     * @return 返回教练信息
     */
    int editCoachScore(CoachScore coachScore);

    /**
     * 学生提交作品
     *
     * @param memberId
     * @param src
     * @return
     */
    int submitProject(String memberId, String src);
}
