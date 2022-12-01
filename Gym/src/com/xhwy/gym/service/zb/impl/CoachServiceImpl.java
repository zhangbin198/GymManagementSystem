package com.xhwy.gym.service.zb.impl;

import com.xhwy.gym.dao.zb.CoachDao;
import com.xhwy.gym.dao.zb.impl.CoachDaoImpl;
import com.xhwy.gym.entity.Coach;
import com.xhwy.gym.service.zb.CoachService;

import java.util.List;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/8/1 20:00
 */
public class CoachServiceImpl implements CoachService {
    CoachDao coachs = new CoachDaoImpl();

    @Override
    public List<Object> allCoach(String page, String limit) {
        return coachs.allCoach(page, limit);
    }

    @Override
    public List<Object> allCoachLike(String page, String limit, String coachId, String coachName, String coachSeff, String coachField) {
        return coachs.allCoachLike(page, limit, coachId, coachName, coachSeff, coachField);
    }

    @Override
    public int countCoach() {
        return coachs.countCoach();
    }

    @Override
    public int editCoach(Coach coach) {
        return coachs.editCoach(coach);
    }

    @Override
    public int addCoach(Coach coach) {
        return coachs.addCoach(coach);
    }

    @Override
    public int delCoach(int coach) {
        return coachs.delCoach(coach);
    }
}
