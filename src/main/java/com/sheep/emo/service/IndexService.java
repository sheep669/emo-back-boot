package com.sheep.emo.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : sheep669
 * @description : 首页的服务接口
 * @created at 2022/9/8 8:16
 */
@Service
public interface IndexService {
    /**
     * 获得饼状图数据
     *
     * @return Map<String, Object>
     * @author sheep669
     * @created at 2022/9/24 14:20
     */
    Map<String, Object> getPieChartData();

    /**
     * 获得订单数据
     *
     * @return Map<String, Object>
     * @author sheep669
     * @created at 2022/9/24 14:20
     */
    Map<String, Object> getOrderData();

    /**
     * 获得库存数据
     *
     * @return Map<String, Object>
     * @author sheep669
     * @created at 2022/9/24 14:20
     */
    Map<String, Object> getInventoryData();

    /**
     * 获得团长数据
     *
     * @return Map<String, Object>
     * @author sheep669
     * @created at 2022/9/24 14:20
     */
    Map<String, Object> getGroupOrganizerData();

    /**
     * 获得售后数据
     *
     * @return Map<String, Object>
     * @author sheep669
     * @created at 2022/9/24 14:20
     */
    Map<String, Object> getAfterSaleData();
}
