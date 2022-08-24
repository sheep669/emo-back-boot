package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.DeliveryPaperMapper;
import com.sheep.emo.pojo.DeliveryPaper;
import com.sheep.emo.service.DeliveryPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作配送单管理的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public class DeliveryPaperServiceImpl implements DeliveryPaperService {

    @Autowired
    private DeliveryPaperMapper deliveryPaperMapper;


    @Override
    public Page<DeliveryPaper> searchOrGetDeliveryPaperList(int current, int size, DeliveryPaper deliveryPaper) {
        Page<DeliveryPaper> page = new Page<>(current, size);
        QueryWrapper<DeliveryPaper> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(deliveryPaper)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(deliveryPaper.getId()) && deliveryPaper.getId() != 0) {
                queryWrapper.eq("id", deliveryPaper.getId());
            }
            if (StrUtil.isNotBlank(deliveryPaper.getStoreName())) {
                queryWrapper.eq("store_name", deliveryPaper.getStoreName());
            }
            if (StrUtil.isNotBlank(deliveryPaper.getDepotName())) {
                queryWrapper.eq("depot_name", deliveryPaper.getDepotName());
            }
            if (StrUtil.isNotBlank(deliveryPaper.getStoreAddress())) {
                queryWrapper.eq("store_address", deliveryPaper.getStoreAddress());
            }
            if (StrUtil.isNotBlank(deliveryPaper.getDeliveryRoutes())) {
                queryWrapper.eq("delivery_routes", deliveryPaper.getDeliveryRoutes());
            }
            if (ObjectUtil.isNotNull(deliveryPaper.getMakeTime())) {
                queryWrapper.eq("make_time", deliveryPaper.getMakeTime());
            }
            if (StrUtil.isNotBlank(deliveryPaper.getDeliveryStatus())) {
                queryWrapper.eq("delivery_status", deliveryPaper.getDeliveryStatus());
            }
            if (ObjectUtil.isNotNull(deliveryPaper.getDeliveryTime())) {
                queryWrapper.eq("delivery_time", deliveryPaper.getDeliveryTime());
            }
            if (ObjectUtil.isNotNull(deliveryPaper.getSignTime())) {
                queryWrapper.eq("sign_time", deliveryPaper.getSignTime());
            }
            if (ObjectUtil.isNotNull(deliveryPaper.getTotalGoodsNumber()) && deliveryPaper.getTotalGoodsNumber() != 0) {
                queryWrapper.eq("total_goods_number", deliveryPaper.getTotalGoodsNumber());
            }
            return deliveryPaperMapper.selectPage(page, queryWrapper);
        }
        return deliveryPaperMapper.selectPage(page, null);
    }


    @Override
    public int deleteDeliveryPaperById(Long id) {
        return deliveryPaperMapper.deleteById(id);
    }

    @Override
    public int deleteDeliveryPaperBatchByIds(Long[] ids) {
        return deliveryPaperMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateDeliveryPaperById(DeliveryPaper deliveryPaper, Long id) {
        LambdaQueryWrapper<DeliveryPaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DeliveryPaper::getId, id);
        return deliveryPaperMapper.update(deliveryPaper, wrapper);
    }

    @Override
    public int addDeliveryPaper(DeliveryPaper deliveryPaper) {
        return deliveryPaperMapper.insert(deliveryPaper);
    }

}

