package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.OrdersMapper;
import com.sheep.emo.pojo.Orders;
import com.sheep.emo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 操作订单的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public Page<Orders> searchOrGetOrdersList(int current, int size, Orders orders) {
        Page<Orders> page = new Page<>(current, size);
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(orders)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(orders.getId()) && orders.getId() != 0) {
                queryWrapper.eq("id", orders.getId());
            }
            if (StrUtil.isNotBlank(orders.getGroupBuyingOrganizerStore())) {
                queryWrapper.eq("group_buying_organizer_store", orders.getGroupBuyingOrganizerStore());
            }
            if (StrUtil.isNotBlank(orders.getGroupBuyingOrganizerName())) {
                queryWrapper.eq("group_buying_organizer_name", orders.getGroupBuyingOrganizerName());
            }
            if (StrUtil.isNotBlank(orders.getGoodsName())) {
                queryWrapper.eq("goods_name", orders.getGoodsName());
            }
            if (ObjectUtil.isNotNull(orders.getOrderAmount()) && !BigDecimal.ZERO.equals(orders.getOrderAmount())) {
                queryWrapper.eq("order_amount", orders.getOrderAmount());
            }
            if (StrUtil.isNotBlank(orders.getGoodsReceivingInformation())) {
                queryWrapper.eq("goods_receiving_information", orders.getGoodsReceivingInformation());
            }
            if (StrUtil.isNotBlank(orders.getOrderStatus())) {
                queryWrapper.eq("order_status", orders.getOrderStatus());
            }
            if (StrUtil.isNotBlank(orders.getOrderRemarks())) {
                queryWrapper.eq("order_remarks", orders.getOrderRemarks());
            }
            if (ObjectUtil.isNotNull(orders.getPurchasingTime())) {
                queryWrapper.eq("purchasing_time", orders.getPurchasingTime());
            }
            return ordersMapper.selectPage(page, queryWrapper);
        }
        return ordersMapper.selectPage(page, null);
    }


    @Override
    public int deleteOrdersById(Long id) {
        return ordersMapper.deleteById(id);
    }

    @Override
    public int deleteOrdersBatchByIds(Long[] ids) {
        return ordersMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateOrdersById(Orders orders, Long id) {
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Orders::getId, id);
        return ordersMapper.update(orders, wrapper);
    }

    @Override
    public int addOrders(Orders orders) {
        return ordersMapper.insert(orders);
    }

}

