package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.MemberLabelMapper;
import com.sheep.emo.pojo.MemberLabel;
import com.sheep.emo.service.MemberLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作会员标签的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public class MemberLabelServiceImpl implements MemberLabelService {

    @Autowired
    private MemberLabelMapper memberLabelMapper;


    @Override
    public Page<MemberLabel> searchOrGetMemberLabelList(int current, int size, MemberLabel memberLabel) {
        Page<MemberLabel> page = new Page<>(current, size);
        QueryWrapper<MemberLabel> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(memberLabel)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(memberLabel.getId()) && memberLabel.getId() != 0) {
                queryWrapper.eq("id", memberLabel.getId());
            }
            if (StrUtil.isNotBlank(memberLabel.getLabelName())) {
                queryWrapper.eq("label_name", memberLabel.getLabelName());
            }
            if (StrUtil.isNotBlank(memberLabel.getDescription())) {
                queryWrapper.eq("description", memberLabel.getDescription());
            }
            if (ObjectUtil.isNotNull(memberLabel.getMemberId()) && memberLabel.getMemberId() != 0) {
                queryWrapper.eq("member_id", memberLabel.getMemberId());
            }
            return memberLabelMapper.selectPage(page, queryWrapper);
        }
        return memberLabelMapper.selectPage(page, null);
    }


    @Override
    public int deleteMemberLabelById(Long id) {
        return memberLabelMapper.deleteById(id);
    }

    @Override
    public int deleteMemberLabelBatchByIds(Long[] ids) {
        return memberLabelMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateMemberLabelById(MemberLabel memberLabel, Long id) {
        LambdaQueryWrapper<MemberLabel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MemberLabel::getId, id);
        return memberLabelMapper.update(memberLabel, wrapper);
    }

    @Override
    public int addMemberLabel(MemberLabel memberLabel) {
        return memberLabelMapper.insert(memberLabel);
    }

}

