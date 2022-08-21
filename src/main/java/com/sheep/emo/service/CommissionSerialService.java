package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.CommissionSerial;
import org.springframework.stereotype.Service;

/**
 * 操作佣金流水的服务接口
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@Service
public interface CommissionSerialService {


    /**
     * 分页获得佣金流水列表或者查询并分页获得佣金流水列表
     *
     * @param current          当前第几页
     * @param size             每页条数
     * @param commissionSerial 查询条件
     * @return Page<CommissionSerial>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<CommissionSerial> searchOrGetCommissionSerialList(int current, int size, CommissionSerial commissionSerial);

    /**
     * 根据id删除指定佣金流水
     *
     * @param id 佣金流水id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteCommissionSerialById(Long id);

    /**
     * 批量删除佣金流水
     *
     * @param ids 佣金流水id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteCommissionSerialBatchByIds(Long[] ids);

    /**
     * 根据id更新指定佣金流水信息
     *
     * @param commissionSerial 佣金流水实体
     * @param id               佣金流水id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateCommissionSerialById(CommissionSerial commissionSerial, Long id);

    /**
     * 添加佣金流水
     *
     * @param commissionSerial 佣金流水实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addCommissionSerial(CommissionSerial commissionSerial);

}

