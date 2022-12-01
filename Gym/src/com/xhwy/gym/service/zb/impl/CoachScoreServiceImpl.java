package com.xhwy.gym.service.zb.impl;

import com.xhwy.gym.dao.zb.CoachScoreDao;
import com.xhwy.gym.dao.zb.impl.CoachScoreDaoImpl;
import com.xhwy.gym.entity.CoachScore;
import com.xhwy.gym.service.zb.CoachScoreService;

import java.util.List;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/8/3 13:50
 */
public class CoachScoreServiceImpl implements CoachScoreService {
    CoachScoreDao coachScores = new CoachScoreDaoImpl();

    @Override
    public List<Object> allCoach(String page, String limit, String coachId) {
        return coachScores.allCoach(page, limit, coachId);
    }

    @Override
    public List<Object> allCoachScore(String page, String limit, String studentId, String studentName, String coachId, String studentScore) {
        return coachScores.allCoachScore(page, limit, studentId, studentName, coachId, studentScore);
    }

    @Override
    public int countCoachScore(String coachId) {
        return coachScores.countCoachScore(coachId);
    }

    @Override
    public int editCoachScore(CoachScore coachScore) {
        return coachScores.editCoachScore(coachScore);
    }

    @Override
    public int submitProject(String memberId, String src) {
        return coachScores.submitProject(memberId, src);
    }

}
