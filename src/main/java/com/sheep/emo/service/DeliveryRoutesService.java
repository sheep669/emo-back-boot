package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.DeliveryRoutes;
import org.springframework.stereotype.Service;

/**
 * 操作配送路线的服务接口
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public interface DeliveryRoutesService {


    /**
     * 分页获得配送路线列表或者查询并分页获得配送路线列表
     *
     * @param current        当前第几页
     * @param size           每页条数
     * @param deliveryRoutes 查询条件
     * @return Page<DeliveryRoutes>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<DeliveryRoutes> searchOrGetDeliveryRoutesList(int current, int size, DeliveryRoutes deliveryRoutes);

    /**
     * 根据id删除指定配送路线
     *
     * @param id 配送路线id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteDeliveryRoutesById(Long id);

    /**
     * 批量删除配送路线
     *
     * @param ids 配送路线id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteDeliveryRoutesBatchByIds(Long[] ids);

    /**
     * 根据id更新指定配送路线信息
     *
     * @param deliveryRoutes 配送路线实体
     * @param id             配送路线id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateDeliveryRoutesById(DeliveryRoutes deliveryRoutes, Long id);

    /**
     * 添加配送路线
     *
     * @param deliveryRoutes 配送路线实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addDeliveryRoutes(DeliveryRoutes deliveryRoutes);

}

