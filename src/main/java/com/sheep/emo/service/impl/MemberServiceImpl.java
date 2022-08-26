package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.MemberMapper;
import com.sheep.emo.pojo.Member;
import com.sheep.emo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作会员的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public Page<Member> searchOrGetMemberList(int current, int size, Member member) {
        Page<Member> page = new Page<>(current, size);
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(member)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(member.getId()) && member.getId() != 0) {
                queryWrapper.eq("id", member.getId());
            }
            if (StrUtil.isNotBlank(member.getUsername())) {
                queryWrapper.eq("username", member.getUsername());
            }
            if (StrUtil.isNotBlank(member.getPassword())) {
                queryWrapper.eq("password", member.getPassword());
            }
            if (StrUtil.isNotBlank(member.getMemberType())) {
                queryWrapper.eq("member_type", member.getMemberType());
            }
            if (StrUtil.isNotBlank(member.getMemberStatus())) {
                queryWrapper.eq("member_status", member.getMemberStatus());
            }
            if (ObjectUtil.isNotNull(member.getValidTime())) {
                queryWrapper.eq("valid_time", member.getValidTime());
            }
            return memberMapper.selectPage(page, queryWrapper);
        }
        return memberMapper.selectPage(page, null);
    }


    @Override
    public int deleteMemberById(Long id) {
        return memberMapper.deleteById(id);
    }

    @Override
    public int deleteMemberBatchByIds(Long[] ids) {
        return memberMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateMemberById(Member member, Long id) {
        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Member::getId, id);
        return memberMapper.update(member, wrapper);
    }

    @Override
    public int addMember(Member member) {
        return memberMapper.insert(member);
    }

    @Override
    public int addBlacklist(Long id) {
        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("member_status", "0");
        return memberMapper.update(null, updateWrapper);
    }

    @Override
    public int removeBlacklist(Long id) {
        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("member_status", "1");
        return memberMapper.update(null, updateWrapper);
    }

}

