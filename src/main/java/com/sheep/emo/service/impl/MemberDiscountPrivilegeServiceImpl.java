package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.MemberDiscountPrivilegeMapper;
import com.sheep.emo.pojo.MemberDiscountPrivilege;
import com.sheep.emo.service.MemberDiscountPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作会员优惠权限的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public class MemberDiscountPrivilegeServiceImpl implements MemberDiscountPrivilegeService {

    @Autowired
    private MemberDiscountPrivilegeMapper memberDiscountPrivilegeMapper;


    @Override
    public Page<MemberDiscountPrivilege> searchOrGetMemberDiscountPrivilegeList(int current, int size, MemberDiscountPrivilege memberDiscountPrivilege) {
        Page<MemberDiscountPrivilege> page = new Page<>(current, size);
        QueryWrapper<MemberDiscountPrivilege> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(memberDiscountPrivilege)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(memberDiscountPrivilege.getId()) && memberDiscountPrivilege.getId() != 0) {
                queryWrapper.eq("id", memberDiscountPrivilege.getId());
            }
            if (StrUtil.isNotBlank(memberDiscountPrivilege.getDiscountType())) {
                queryWrapper.eq("discount_type", memberDiscountPrivilege.getDiscountType());
            }
            if (ObjectUtil.isNotNull(memberDiscountPrivilege.getMemberId()) && memberDiscountPrivilege.getMemberId() != 0) {
                queryWrapper.eq("member_id", memberDiscountPrivilege.getMemberId());
            }
            if (StrUtil.isNotBlank(memberDiscountPrivilege.getStatus())) {
                queryWrapper.eq("status", memberDiscountPrivilege.getStatus());
            }
            return memberDiscountPrivilegeMapper.selectPage(page, queryWrapper);
        }
        return memberDiscountPrivilegeMapper.selectPage(page, null);
    }


    @Override
    public int deleteMemberDiscountPrivilegeById(Long id) {
        return memberDiscountPrivilegeMapper.deleteById(id);
    }

    @Override
    public int deleteMemberDiscountPrivilegeBatchByIds(Long[] ids) {
        return memberDiscountPrivilegeMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateMemberDiscountPrivilegeById(MemberDiscountPrivilege memberDiscountPrivilege, Long id) {
        LambdaQueryWrapper<MemberDiscountPrivilege> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MemberDiscountPrivilege::getId, id);
        return memberDiscountPrivilegeMapper.update(memberDiscountPrivilege, wrapper);
    }

    @Override
    public int addMemberDiscountPrivilege(MemberDiscountPrivilege memberDiscountPrivilege) {
        return memberDiscountPrivilegeMapper.insert(memberDiscountPrivilege);
    }

}

