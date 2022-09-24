package com.sheep.emo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheep.emo.pojo.Orders;

import java.util.List;

/**
 * 操作订单的mapper接口
 *
 * @author sheep669
 * @created at 2022-08-20
 */

public interface OrdersMapper extends BaseMapper<Orders> {
    Long countTodayOrderNum();

    List<Orders> getBuyerRank();
}

