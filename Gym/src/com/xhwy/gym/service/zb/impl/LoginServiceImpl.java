package com.xhwy.gym.service.zb.impl;

import com.xhwy.gym.dao.zb.LoginDao;
import com.xhwy.gym.dao.zb.impl.LoginDaoImpl;
import com.xhwy.gym.entity.Admin;
import com.xhwy.gym.entity.Coach;
import com.xhwy.gym.entity.MemberAll;
import com.xhwy.gym.service.zb.LoginService;

/**
 * 管理员登录业务实现层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/18 6:52
 */
public class LoginServiceImpl implements LoginService {
    //创建管理员登录数据访问层
    LoginDao gym = new LoginDaoImpl();

    @Override
    public Admin login(String username, String password) {
        //返回拆查询所有账号和密码
        return gym.login(username, password);
    }

    @Override
    public MemberAll memberLogin(String memberAccount, String memberPassword) {
        return gym.memberLogin(memberAccount, memberPassword);
    }

    @Override
    public Coach coachLogin(String coachId, String coachPassword) {
        return gym.coachLogin(coachId, coachPassword);
    }

    @Override
    public int addLogin(Admin admin) {
        //添加管理员
        return gym.addLogin(admin);
    }
}
