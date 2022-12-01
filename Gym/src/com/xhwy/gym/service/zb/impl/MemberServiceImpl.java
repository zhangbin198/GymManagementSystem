package com.xhwy.gym.service.zb.impl;

import com.xhwy.gym.dao.zb.MemberDao;
import com.xhwy.gym.dao.zb.impl.MemberDaoImpl;
import com.xhwy.gym.entity.MemberAll;
import com.xhwy.gym.service.zb.MemberService;

import java.util.List;

/**
 * 会员信息业务数据实现层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/19 10:16
 */
public class MemberServiceImpl implements MemberService {
    //创建会员数据访问层
    MemberDao members = new MemberDaoImpl();

    @Override
    public List<Object> allMembers(String page, String limit) {
        //查询所有会员信息
        return members.allMembers(page, limit);
    }

    @Override
    public List<Object> allMembersLike(String page, String limit, String memberAccount, String memberName, String memberPhone, String cardTime) {
        //返回模糊查询所有会员信息
        return members.allMembersLike(page, limit, memberAccount, memberName, memberPhone, cardTime);
    }

    @Override
    public int countMembers() {
        //返回查询所有会员信息总数
        return members.countMembers();
    }

    @Override
    public int editMember(MemberAll memberAll) {
        //编辑会员信息
        return this.members.editMember(memberAll);
    }

    @Override
    public int addMember(MemberAll memberAll) {
        //添加会员信息
        return this.members.addMember(memberAll);
    }

    @Override
    public int delMember(int memberAccount) {
        //删除会员信息
        return members.delMember(memberAccount);
    }
}
