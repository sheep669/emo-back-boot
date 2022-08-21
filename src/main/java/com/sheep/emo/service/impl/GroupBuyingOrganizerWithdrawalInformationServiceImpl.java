package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.GroupBuyingOrganizerWithdrawalInformationMapper;
import com.sheep.emo.pojo.GroupBuyingOrganizerWithdrawalInformation;
import com.sheep.emo.service.GroupBuyingOrganizerWithdrawalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 操作团长提现信息的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@Service
public class GroupBuyingOrganizerWithdrawalInformationServiceImpl implements GroupBuyingOrganizerWithdrawalInformationService {

    @Autowired
    private GroupBuyingOrganizerWithdrawalInformationMapper groupBuyingOrganizerWithdrawalInformationMapper;


    @Override
    public Page<GroupBuyingOrganizerWithdrawalInformation> searchOrGetGroupBuyingOrganizerWithdrawalInformationList(int current, int size, GroupBuyingOrganizerWithdrawalInformation groupBuyingOrganizerWithdrawalInformation) {
        Page<GroupBuyingOrganizerWithdrawalInformation> page = new Page<>(current, size);
        QueryWrapper<GroupBuyingOrganizerWithdrawalInformation> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(groupBuyingOrganizerWithdrawalInformation)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(groupBuyingOrganizerWithdrawalInformation.getId()) && groupBuyingOrganizerWithdrawalInformation.getId() != 0) {
                queryWrapper.eq("id", groupBuyingOrganizerWithdrawalInformation.getId());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerWithdrawalInformation.getGroupBuyingOrganizerName())) {
                queryWrapper.eq("group_buying_organizer_name", groupBuyingOrganizerWithdrawalInformation.getGroupBuyingOrganizerName());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerWithdrawalInformation.getPhoneNumber())) {
                queryWrapper.eq("phone_number", groupBuyingOrganizerWithdrawalInformation.getPhoneNumber());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerWithdrawalInformation.getStoreName())) {
                queryWrapper.eq("store_name", groupBuyingOrganizerWithdrawalInformation.getStoreName());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerWithdrawalInformation.getStoreAddress())) {
                queryWrapper.eq("store_address", groupBuyingOrganizerWithdrawalInformation.getStoreAddress());
            }
            if (ObjectUtil.isNotNull(groupBuyingOrganizerWithdrawalInformation.getCashBalance()) && !BigDecimal.ZERO.equals(groupBuyingOrganizerWithdrawalInformation.getCashBalance())) {
                queryWrapper.eq("cash_balance", groupBuyingOrganizerWithdrawalInformation.getCashBalance());
            }
            if (ObjectUtil.isNotNull(groupBuyingOrganizerWithdrawalInformation.getCommissionBalance()) && !BigDecimal.ZERO.equals(groupBuyingOrganizerWithdrawalInformation.getCommissionBalance())) {
                queryWrapper.eq("commission_balance", groupBuyingOrganizerWithdrawalInformation.getCommissionBalance());
            }
            if (ObjectUtil.isNotNull(groupBuyingOrganizerWithdrawalInformation.getDrawnMoney()) && !BigDecimal.ZERO.equals(groupBuyingOrganizerWithdrawalInformation.getDrawnMoney())) {
                queryWrapper.eq("drawn_money", groupBuyingOrganizerWithdrawalInformation.getDrawnMoney());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerWithdrawalInformation.getArrivalAmountType())) {
                queryWrapper.eq("arrival_amount_type", groupBuyingOrganizerWithdrawalInformation.getArrivalAmountType());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerWithdrawalInformation.getAccountInformation())) {
                queryWrapper.eq("account_information", groupBuyingOrganizerWithdrawalInformation.getAccountInformation());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizerWithdrawalInformation.getAuditStatus())) {
                queryWrapper.eq("audit_status", groupBuyingOrganizerWithdrawalInformation.getAuditStatus());
            }
            if (ObjectUtil.isNotNull(groupBuyingOrganizerWithdrawalInformation.getApplyTime())) {
                queryWrapper.eq("apply_time", groupBuyingOrganizerWithdrawalInformation.getApplyTime());
            }
            return groupBuyingOrganizerWithdrawalInformationMapper.selectPage(page, queryWrapper);
        }
        return groupBuyingOrganizerWithdrawalInformationMapper.selectPage(page, null);
    }


    @Override
    public int deleteGroupBuyingOrganizerWithdrawalInformationById(Long id) {
        return groupBuyingOrganizerWithdrawalInformationMapper.deleteById(id);
    }

    @Override
    public int deleteGroupBuyingOrganizerWithdrawalInformationBatchByIds(Long[] ids) {
        return groupBuyingOrganizerWithdrawalInformationMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateGroupBuyingOrganizerWithdrawalInformationById(GroupBuyingOrganizerWithdrawalInformation groupBuyingOrganizerWithdrawalInformation, Long id) {
        LambdaQueryWrapper<GroupBuyingOrganizerWithdrawalInformation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GroupBuyingOrganizerWithdrawalInformation::getId, id);
        return groupBuyingOrganizerWithdrawalInformationMapper.update(groupBuyingOrganizerWithdrawalInformation, wrapper);
    }

    @Override
    public int addGroupBuyingOrganizerWithdrawalInformation(GroupBuyingOrganizerWithdrawalInformation groupBuyingOrganizerWithdrawalInformation) {
        return groupBuyingOrganizerWithdrawalInformationMapper.insert(groupBuyingOrganizerWithdrawalInformation);
    }

    @Override
    public int confirmAudit(Long id) {
        UpdateWrapper<GroupBuyingOrganizerWithdrawalInformation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "2");
        return groupBuyingOrganizerWithdrawalInformationMapper.update(null, updateWrapper);
    }

    @Override
    public int approveAudit(Long id) {
        UpdateWrapper<GroupBuyingOrganizerWithdrawalInformation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "3");
        return groupBuyingOrganizerWithdrawalInformationMapper.update(null, updateWrapper);
    }

    @Override
    public int rejectAudit(Long id) {
        UpdateWrapper<GroupBuyingOrganizerWithdrawalInformation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "4");
        return groupBuyingOrganizerWithdrawalInformationMapper.update(null, updateWrapper);
    }

    @Override
    public int reAudit(Long id) {
        UpdateWrapper<GroupBuyingOrganizerWithdrawalInformation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "1");
        return groupBuyingOrganizerWithdrawalInformationMapper.update(null, updateWrapper);
    }

}

