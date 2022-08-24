package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.DeliveryRoutesMapper;
import com.sheep.emo.pojo.DeliveryRoutes;
import com.sheep.emo.service.DeliveryRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作配送路线的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public class DeliveryRoutesServiceImpl implements DeliveryRoutesService {

    @Autowired
    private DeliveryRoutesMapper deliveryRoutesMapper;


    @Override
    public Page<DeliveryRoutes> searchOrGetDeliveryRoutesList(int current, int size, DeliveryRoutes deliveryRoutes) {
        Page<DeliveryRoutes> page = new Page<>(current, size);
        QueryWrapper<DeliveryRoutes> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(deliveryRoutes)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(deliveryRoutes.getId()) && deliveryRoutes.getId() != 0) {
                queryWrapper.eq("id", deliveryRoutes.getId());
            }
            if (StrUtil.isNotBlank(deliveryRoutes.getDeliveryRoute())) {
                queryWrapper.eq("delivery_route", deliveryRoutes.getDeliveryRoute());
            }
            if (StrUtil.isNotBlank(deliveryRoutes.getDeliveryMan())) {
                queryWrapper.eq("delivery_man", deliveryRoutes.getDeliveryMan());
            }
            if (StrUtil.isNotBlank(deliveryRoutes.getPhoneNumber())) {
                queryWrapper.eq("phone_number", deliveryRoutes.getPhoneNumber());
            }
            return deliveryRoutesMapper.selectPage(page, queryWrapper);
        }
        return deliveryRoutesMapper.selectPage(page, null);
    }


    @Override
    public int deleteDeliveryRoutesById(Long id) {
        return deliveryRoutesMapper.deleteById(id);
    }

    @Override
    public int deleteDeliveryRoutesBatchByIds(Long[] ids) {
        return deliveryRoutesMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateDeliveryRoutesById(DeliveryRoutes deliveryRoutes, Long id) {
        LambdaQueryWrapper<DeliveryRoutes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DeliveryRoutes::getId, id);
        return deliveryRoutesMapper.update(deliveryRoutes, wrapper);
    }

    @Override
    public int addDeliveryRoutes(DeliveryRoutes deliveryRoutes) {
        return deliveryRoutesMapper.insert(deliveryRoutes);
    }

}

