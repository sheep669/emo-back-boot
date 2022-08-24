package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.AfterSale;
import org.springframework.stereotype.Service;

/**
 * 操作售后的服务接口
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public interface AfterSaleService {


    /**
     * 分页获得售后列表或者查询并分页获得售后列表
     *
     * @param current   当前第几页
     * @param size      每页条数
     * @param afterSale 查询条件
     * @return Page<AfterSale>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<AfterSale> searchOrGetAfterSaleList(int current, int size, AfterSale afterSale);

    /**
     * 根据id删除指定售后
     *
     * @param id 售后id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteAfterSaleById(Long id);

    /**
     * 批量删除售后
     *
     * @param ids 售后id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteAfterSaleBatchByIds(Long[] ids);

    /**
     * 根据id更新指定售后信息
     *
     * @param afterSale 售后实体
     * @param id        售后id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateAfterSaleById(AfterSale afterSale, Long id);

    /**
     * 添加售后
     *
     * @param afterSale 售后实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addAfterSale(AfterSale afterSale);

    /**
     * 确认售后申请审核
     *
     * @param id 售后申请id
     * @return int
     * @author sheep669
     * @created at 2022/8/15 17:47
     */
    int confirmAudit(Long id);

    /**
     * 通过审核
     *
     * @param id 售后申请id
     * @return int
     * @author sheep669
     * @created at 2022/8/16 19:54
     */
    int approveAudit(Long id);


    /**
     * 拒绝审核
     *
     * @param id 售后申请id
     * @return int
     * @author sheep669
     * @created at 2022/8/16 19:55
     */
    int rejectAudit(Long id);

    /**
     * 重新审核
     *
     * @param id 售后申请id
     * @return int
     * @author sheep669
     * @created at 2022/8/20 10:06
     */
    int reAudit(Long id);

}

