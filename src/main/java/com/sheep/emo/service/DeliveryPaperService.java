package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.DeliveryPaper;
import org.springframework.stereotype.Service;

/**
 * 操作配送单管理的服务接口
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public interface DeliveryPaperService {


    /**
     * 分页获得配送单管理列表或者查询并分页获得配送单管理列表
     *
     * @param current       当前第几页
     * @param size          每页条数
     * @param deliveryPaper 查询条件
     * @return Page<DeliveryPaper>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<DeliveryPaper> searchOrGetDeliveryPaperList(int current, int size, DeliveryPaper deliveryPaper);

    /**
     * 根据id删除指定配送单管理
     *
     * @param id 配送单管理id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteDeliveryPaperById(Long id);

    /**
     * 批量删除配送单管理
     *
     * @param ids 配送单管理id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteDeliveryPaperBatchByIds(Long[] ids);

    /**
     * 根据id更新指定配送单管理信息
     *
     * @param deliveryPaper 配送单管理实体
     * @param id            配送单管理id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateDeliveryPaperById(DeliveryPaper deliveryPaper, Long id);

    /**
     * 添加配送单管理
     *
     * @param deliveryPaper 配送单管理实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addDeliveryPaper(DeliveryPaper deliveryPaper);

}

