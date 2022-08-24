package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.AfterSaleMapper;
import com.sheep.emo.pojo.AfterSale;
import com.sheep.emo.service.AfterSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 操作售后的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public class AfterSaleServiceImpl implements AfterSaleService {

    @Autowired
    private AfterSaleMapper afterSaleMapper;


    @Override
    public Page<AfterSale> searchOrGetAfterSaleList(int current, int size, AfterSale afterSale) {
        Page<AfterSale> page = new Page<>(current, size);
        QueryWrapper<AfterSale> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(afterSale)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(afterSale.getId()) && afterSale.getId() != 0) {
                queryWrapper.eq("id", afterSale.getId());
            }
            if (StrUtil.isNotBlank(afterSale.getOrderNo())) {
                queryWrapper.eq("order_no", afterSale.getOrderNo());
            }
            if (ObjectUtil.isNotNull(afterSale.getOrderId()) && afterSale.getOrderId() != 0) {
                queryWrapper.eq("order_id", afterSale.getOrderId());
            }
            if (ObjectUtil.isNotNull(afterSale.getGoodsId()) && afterSale.getGoodsId() != 0) {
                queryWrapper.eq("goods_id", afterSale.getGoodsId());
            }
            if (ObjectUtil.isNotNull(afterSale.getUserId()) && afterSale.getUserId() != 0) {
                queryWrapper.eq("user_id", afterSale.getUserId());
            }
            if (StrUtil.isNotBlank(afterSale.getStoreName())) {
                queryWrapper.eq("store_name", afterSale.getStoreName());
            }
            if (StrUtil.isNotBlank(afterSale.getGoodsName())) {
                queryWrapper.eq("goods_name", afterSale.getGoodsName());
            }
            if (StrUtil.isNotBlank(afterSale.getBuyerName())) {
                queryWrapper.eq("buyer_name", afterSale.getBuyerName());
            }
            if (StrUtil.isNotBlank(afterSale.getAuditStatus())) {
                queryWrapper.eq("audit_status", afterSale.getAuditStatus());
            }
            if (StrUtil.isNotBlank(afterSale.getOrderAfterSaleType())) {
                queryWrapper.eq("order_after_sale_type", afterSale.getOrderAfterSaleType());
            }
            if (StrUtil.isNotBlank(afterSale.getRefundType())) {
                queryWrapper.eq("refund_type", afterSale.getRefundType());
            }
            if (StrUtil.isNotBlank(afterSale.getReason())) {
                queryWrapper.eq("reason", afterSale.getReason());
            }
            if (ObjectUtil.isNotNull(afterSale.getOrderAfterSaleNumber()) && afterSale.getOrderAfterSaleNumber() != 0) {
                queryWrapper.eq("order_after_sale_number", afterSale.getOrderAfterSaleNumber());
            }
            if (ObjectUtil.isNotNull(afterSale.getPrice()) && !BigDecimal.ZERO.equals(afterSale.getPrice())) {
                queryWrapper.eq("price", afterSale.getPrice());
            }
            if (StrUtil.isNotBlank(afterSale.getRefundInstruction())) {
                queryWrapper.eq("refund_instruction", afterSale.getRefundInstruction());
            }
            if (StrUtil.isNotBlank(afterSale.getImages())) {
                queryWrapper.eq("images", afterSale.getImages());
            }
            if (ObjectUtil.isNotNull(afterSale.getApplyTime())) {
                queryWrapper.eq("apply_time", afterSale.getApplyTime());
            }
            if (ObjectUtil.isNotNull(afterSale.getDeliveryTime())) {
                queryWrapper.eq("delivery_time", afterSale.getDeliveryTime());
            }
            return afterSaleMapper.selectPage(page, queryWrapper);
        }
        return afterSaleMapper.selectPage(page, null);
    }


    @Override
    public int deleteAfterSaleById(Long id) {
        return afterSaleMapper.deleteById(id);
    }

    @Override
    public int deleteAfterSaleBatchByIds(Long[] ids) {
        return afterSaleMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateAfterSaleById(AfterSale afterSale, Long id) {
        LambdaQueryWrapper<AfterSale> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AfterSale::getId, id);
        return afterSaleMapper.update(afterSale, wrapper);
    }

    @Override
    public int addAfterSale(AfterSale afterSale) {
        return afterSaleMapper.insert(afterSale);
    }

    @Override
    public int confirmAudit(Long id) {
        UpdateWrapper<AfterSale> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "2");
        return afterSaleMapper.update(null, updateWrapper);
    }

    @Override
    public int approveAudit(Long id) {
        UpdateWrapper<AfterSale> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "3");
        return afterSaleMapper.update(null, updateWrapper);
    }

    @Override
    public int rejectAudit(Long id) {
        UpdateWrapper<AfterSale> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "4");
        return afterSaleMapper.update(null, updateWrapper);
    }

    @Override
    public int reAudit(Long id) {
        UpdateWrapper<AfterSale> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "1");
        return afterSaleMapper.update(null, updateWrapper);
    }
}

