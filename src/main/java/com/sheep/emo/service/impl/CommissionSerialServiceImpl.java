package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.CommissionSerialMapper;
import com.sheep.emo.pojo.CommissionSerial;
import com.sheep.emo.service.CommissionSerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 操作佣金流水的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@Service
public class CommissionSerialServiceImpl implements CommissionSerialService {

    @Autowired
    private CommissionSerialMapper commissionSerialMapper;


    @Override
    public Page<CommissionSerial> searchOrGetCommissionSerialList(int current, int size, CommissionSerial commissionSerial) {
        Page<CommissionSerial> page = new Page<>(current, size);
        QueryWrapper<CommissionSerial> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(commissionSerial)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(commissionSerial.getId()) && commissionSerial.getId() != 0) {
                queryWrapper.eq("id", commissionSerial.getId());
            }
            if (StrUtil.isNotBlank(commissionSerial.getGroupBuyingOrganizerName())) {
                queryWrapper.eq("group_buying_organizer_name", commissionSerial.getGroupBuyingOrganizerName());
            }
            if (StrUtil.isNotBlank(commissionSerial.getStoreName())) {
                queryWrapper.eq("store_name", commissionSerial.getStoreName());
            }
            if (ObjectUtil.isNotNull(commissionSerial.getCommission()) && !BigDecimal.ZERO.equals(commissionSerial.getCommission())) {
                queryWrapper.eq("commission", commissionSerial.getCommission());
            }
            if (StrUtil.isNotBlank(commissionSerial.getType())) {
                queryWrapper.eq("type", commissionSerial.getType());
            }
            if (ObjectUtil.isNotNull(commissionSerial.getMakeTime())) {
                queryWrapper.eq("make_time", commissionSerial.getMakeTime());
            }
            if (StrUtil.isNotBlank(commissionSerial.getRemark())) {
                queryWrapper.eq("remark", commissionSerial.getRemark());
            }
            return commissionSerialMapper.selectPage(page, queryWrapper);
        }
        return commissionSerialMapper.selectPage(page, null);
    }


    @Override
    public int deleteCommissionSerialById(Long id) {
        return commissionSerialMapper.deleteById(id);
    }

    @Override
    public int deleteCommissionSerialBatchByIds(Long[] ids) {
        return commissionSerialMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateCommissionSerialById(CommissionSerial commissionSerial, Long id) {
        LambdaQueryWrapper<CommissionSerial> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommissionSerial::getId, id);
        return commissionSerialMapper.update(commissionSerial, wrapper);
    }

    @Override
    public int addCommissionSerial(CommissionSerial commissionSerial) {
        return commissionSerialMapper.insert(commissionSerial);
    }

}

