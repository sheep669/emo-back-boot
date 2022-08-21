package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.GroupBuyingOrganizerClearMapper;
import com.sheep.emo.pojo.GroupBuyingOrganizerClear;
import com.sheep.emo.service.GroupBuyingOrganizerClearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 操作团长结算的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@Service
public class GroupBuyingOrganizerClearServiceImpl implements GroupBuyingOrganizerClearService {

    @Autowired
    private GroupBuyingOrganizerClearMapper groupBuyingOrganizerClearMapper;


    @Override
    public Page<GroupBuyingOrganizerClear> searchOrGetGroupBuyingOrganizerClearList(int current, int size, GroupBuyingOrganizerClear groupBuyingOrganizerClear) {
        Page<GroupBuyingOrganizerClear> page = new Page<>(current, size);
        QueryWrapper<GroupBuyingOrganizerClear> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(groupBuyingOrganizerClear)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(groupBuyingOrganizerClear.getId()) && groupBuyingOrganizerClear.getId() != 0) {
                queryWrapper.eq("id", groupBuyingOrganizerClear.getId());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerClear.getGroupBuyingOrganizerName())) {
                queryWrapper.eq("group_buying_organizer_name", groupBuyingOrganizerClear.getGroupBuyingOrganizerName());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerClear.getPhoneNumber())) {
                queryWrapper.eq("phone_number", groupBuyingOrganizerClear.getPhoneNumber());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerClear.getStoreName())) {
                queryWrapper.eq("store_name", groupBuyingOrganizerClear.getStoreName());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerClear.getStoreAddress())) {
                queryWrapper.eq("store_address", groupBuyingOrganizerClear.getStoreAddress());
            }
            if (ObjectUtil.isNotNull(groupBuyingOrganizerClear.getBalancedCommission()) && !BigDecimal.ZERO.equals(groupBuyingOrganizerClear.getBalancedCommission())) {
                queryWrapper.eq("balanced_commission", groupBuyingOrganizerClear.getBalancedCommission());
            }
            if (ObjectUtil.isNotNull(groupBuyingOrganizerClear.getUnbalancedCommission()) && !BigDecimal.ZERO.equals(groupBuyingOrganizerClear.getUnbalancedCommission())) {
                queryWrapper.eq("unbalanced_commission", groupBuyingOrganizerClear.getUnbalancedCommission());
            }
            return groupBuyingOrganizerClearMapper.selectPage(page, queryWrapper);
        }
        return groupBuyingOrganizerClearMapper.selectPage(page, null);
    }


    @Override
    public int deleteGroupBuyingOrganizerClearById(Long id) {
        return groupBuyingOrganizerClearMapper.deleteById(id);
    }

    @Override
    public int deleteGroupBuyingOrganizerClearBatchByIds(Long[] ids) {
        return groupBuyingOrganizerClearMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateGroupBuyingOrganizerClearById(GroupBuyingOrganizerClear groupBuyingOrganizerClear, Long id) {
        LambdaQueryWrapper<GroupBuyingOrganizerClear> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GroupBuyingOrganizerClear::getId, id);
        return groupBuyingOrganizerClearMapper.update(groupBuyingOrganizerClear, wrapper);
    }

    @Override
    public int addGroupBuyingOrganizerClear(GroupBuyingOrganizerClear groupBuyingOrganizerClear) {
        return groupBuyingOrganizerClearMapper.insert(groupBuyingOrganizerClear);
    }

}

