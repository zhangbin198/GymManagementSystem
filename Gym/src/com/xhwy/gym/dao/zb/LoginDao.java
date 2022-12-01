package com.xhwy.gym.dao.zb;

import com.xhwy.gym.entity.Admin;
import com.xhwy.gym.entity.Coach;
import com.xhwy.gym.entity.MemberAll;

/**
 * 管理员登录数据访问层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/18 6:37
 */
public interface LoginDao {
    /**
     * 管理员登录
     *
     * @param username 账号
     * @param password 密码
     * @return 返回一个管理员
     */
    Admin login(String username, String password);

    /**
     * 管理员注册
     *
     * @param admin
     * @return
     */
    int addLogin(Admin admin);

    /**
     * 会员登录
     *
     * @param memberAccount  会员账号
     * @param memberPassword 会员密码
     * @return
     */
    MemberAll memberLogin(String memberAccount, String memberPassword);

    /**
     * 教练登录
     *
     * @param coachId       教练账号
     * @param coachPassword 教练密码
     * @return
     */
    Coach coachLogin(String coachId, String coachPassword);
}
