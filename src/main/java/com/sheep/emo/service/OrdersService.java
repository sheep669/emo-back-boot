package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作订单的服务接口
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@Service
public interface OrdersService {


    /**
     * 分页获得订单列表或者查询并分页获得订单列表
     *
     * @param current 当前第几页
     * @param size    每页条数
     * @param orders  查询条件
     * @return Page<Orders>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<Orders> searchOrGetOrdersList(int current, int size, Orders orders);

    /**
     * 根据id删除指定订单
     *
     * @param id 订单id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteOrdersById(Long id);

    /**
     * 批量删除订单
     *
     * @param ids 订单id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteOrdersBatchByIds(Long[] ids);

    /**
     * 根据id更新指定订单信息
     *
     * @param orders 订单实体
     * @param id     订单id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateOrdersById(Orders orders, Long id);

    /**
     * 添加订单
     *
     * @param orders 订单实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addOrders(Orders orders);

    /**
     * 统计当日订单
     *
     * @return int
     * @author sheep669
     * @created at 2022/9/24 16:38
     */
    Long countTodayOrderNum();

    List<Orders> getBuyerRank();
}

