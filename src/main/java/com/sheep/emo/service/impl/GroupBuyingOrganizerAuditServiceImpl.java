package com.sheep.emo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.GroupBuyingOrganizerAuditMapper;
import com.sheep.emo.pojo.GroupBuyingOrganizerAudit;
import com.sheep.emo.service.GroupBuyingOrganizerAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * TODO
 *
 * @author sheep669
 * @created at 2022-08-12
 */

@Service
public class GroupBuyingOrganizerAuditServiceImpl implements GroupBuyingOrganizerAuditService {

    @Autowired
    private GroupBuyingOrganizerAuditMapper groupBuyingOrganizerAuditMapper;

    @Override
    public Page<GroupBuyingOrganizerAudit> searchOrGetGroupBuyingOrganizerAuditList(int current, int size, GroupBuyingOrganizerAudit groupBuyingOrganizerAudit) {
        Page<GroupBuyingOrganizerAudit> page = new Page<>(current, size);
        QueryWrapper<GroupBuyingOrganizerAudit> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(groupBuyingOrganizerAudit)) {
            // 按需添加过滤条件   模糊查询 like 查询 eq
            if (ObjectUtil.isNotNull(groupBuyingOrganizerAudit.getId())) {
                queryWrapper.eq("id", groupBuyingOrganizerAudit.getId());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerAudit.getStoreName())) {
                queryWrapper.like("store_name", groupBuyingOrganizerAudit.getStoreName());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerAudit.getGroupBuyingOrganizerName())) {
                queryWrapper.like("group_buying_organizer_name", groupBuyingOrganizerAudit.getGroupBuyingOrganizerName());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerAudit.getPhoneNumber())) {
                queryWrapper.eq("phone_number", groupBuyingOrganizerAudit.getPhoneNumber());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerAudit.getReferrer())) {
                queryWrapper.like("referrer", groupBuyingOrganizerAudit.getReferrer());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerAudit.getAuditStatus())) {
                queryWrapper.eq("audit_status", groupBuyingOrganizerAudit.getAuditStatus());
            }
            // if (StrUtil.isNotBlank(groupBuyingOrganizerAudit.getApplyTime().toString())) {
            //     queryWrapper.eq("apply_time", groupBuyingOrganizerAudit.getApplyTime());
            // }
            if (StrUtil.isNotBlank(groupBuyingOrganizerAudit.getDetailAddress())) {
                queryWrapper.like("detail_address", groupBuyingOrganizerAudit.getDetailAddress());
            }
            return groupBuyingOrganizerAuditMapper.selectPage(page, queryWrapper);
        }
        return groupBuyingOrganizerAuditMapper.selectPage(page, null);
    }


    @Override
    public int deleteGroupBuyingOrganizerAuditById(Long id) {
        return groupBuyingOrganizerAuditMapper.deleteById(id);
    }

    @Override
    public int deleteGroupBuyingOrganizerAuditBatchByIds(Long[] ids) {
        return groupBuyingOrganizerAuditMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateGroupBuyingOrganizerAuditById(GroupBuyingOrganizerAudit groupBuyingOrganizerAudit, Long id) {
        LambdaQueryWrapper<GroupBuyingOrganizerAudit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GroupBuyingOrganizerAudit::getId, id);
        return groupBuyingOrganizerAuditMapper.update(groupBuyingOrganizerAudit, wrapper);
    }

    @Override
    public int addGroupBuyingOrganizerAudit(GroupBuyingOrganizerAudit groupBuyingOrganizerAudit) {
        return groupBuyingOrganizerAuditMapper.insert(groupBuyingOrganizerAudit);
    }

}

