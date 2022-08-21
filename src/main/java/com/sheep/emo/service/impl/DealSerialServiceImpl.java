package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.DealSerialMapper;
import com.sheep.emo.pojo.DealSerial;
import com.sheep.emo.service.DealSerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 操作交易流水的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@Service
public class DealSerialServiceImpl implements DealSerialService {

    @Autowired
    private DealSerialMapper dealSerialMapper;


    @Override
    public Page<DealSerial> searchOrGetDealSerialList(int current, int size, DealSerial dealSerial) {
        Page<DealSerial> page = new Page<>(current, size);
        QueryWrapper<DealSerial> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(dealSerial)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(dealSerial.getId()) && dealSerial.getId() != 0) {
                queryWrapper.eq("id", dealSerial.getId());
            }
            if (ObjectUtil.isNotNull(dealSerial.getPaySerialId()) && dealSerial.getPaySerialId() != 0) {
                queryWrapper.eq("pay_serial_id", dealSerial.getPaySerialId());
            }
            if (ObjectUtil.isNotNull(dealSerial.getTotalOrderAmount()) && !BigDecimal.ZERO.equals(dealSerial.getTotalOrderAmount())) {
                queryWrapper.eq("total_order_amount", dealSerial.getTotalOrderAmount());
            }
            if (ObjectUtil.isNotNull(dealSerial.getRefundAmount()) && !BigDecimal.ZERO.equals(dealSerial.getRefundAmount())) {
                queryWrapper.eq("refund_amount", dealSerial.getRefundAmount());
            }
            if (ObjectUtil.isNotNull(dealSerial.getRealAmount()) && !BigDecimal.ZERO.equals(dealSerial.getRealAmount())) {
                queryWrapper.eq("real_amount", dealSerial.getRealAmount());
            }
            if (StrUtil.isNotBlank(dealSerial.getStoreName())) {
                queryWrapper.eq("store_name", dealSerial.getStoreName());
            }
            return dealSerialMapper.selectPage(page, queryWrapper);
        }
        return dealSerialMapper.selectPage(page, null);
    }


    @Override
    public int deleteDealSerialById(Long id) {
        return dealSerialMapper.deleteById(id);
    }

    @Override
    public int deleteDealSerialBatchByIds(Long[] ids) {
        return dealSerialMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateDealSerialById(DealSerial dealSerial, Long id) {
        LambdaQueryWrapper<DealSerial> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DealSerial::getId, id);
        return dealSerialMapper.update(dealSerial, wrapper);
    }

    @Override
    public int addDealSerial(DealSerial dealSerial) {
        return dealSerialMapper.insert(dealSerial);
    }

}

