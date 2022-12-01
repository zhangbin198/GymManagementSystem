package com.xhwy.gym.service.zb;

import com.xhwy.gym.entity.MemberAll;

import java.util.List;

/**
 * 会员信息业务访问层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/19 10:15
 */
public interface MemberService {

    /**
     * 查询所有数据并分页
     *
     * @param page  第几页
     * @param limit 每页显示多少条数据
     * @return 查询返回的数据集合
     */
    List<Object> allMembers(String page, String limit);

    /**
     * 模糊查询并分页
     *
     * @param memberName 根据会员姓名模糊查询
     * @return 返回查询到的会员信息
     */
    List<Object> allMembersLike(String page, String limit, String memberAccount, String memberName, String memberPhone, String cardTime);

    /**
     * 获取会员信息总和
     *
     * @return 返回会员信息总和
     */
    int countMembers();

    /**
     * 编辑会员信息
     *
     * @param memberAll 会员信息对象
     * @return 返回会员信息
     */
    int editMember(MemberAll memberAll);

    /**
     * 添加会员信息
     *
     * @param memberAll 会员信息对象
     * @return 返回受影响行数
     */
    int addMember(MemberAll memberAll);

    /**
     * 根据编号删除指定会员信息
     *
     * @param memberAccount 会员账号
     * @return 返回受影响行数
     */
    int delMember(int memberAccount);
}
