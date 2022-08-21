package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.DealSerial;
import org.springframework.stereotype.Service;

/**
 * 操作交易流水的服务接口
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@Service
public interface DealSerialService {


    /**
     * 分页获得交易流水列表或者查询并分页获得交易流水列表
     *
     * @param current    当前第几页
     * @param size       每页条数
     * @param dealSerial 查询条件
     * @return Page<DealSerial>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<DealSerial> searchOrGetDealSerialList(int current, int size, DealSerial dealSerial);

    /**
     * 根据id删除指定交易流水
     *
     * @param id 交易流水id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteDealSerialById(Long id);

    /**
     * 批量删除交易流水
     *
     * @param ids 交易流水id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteDealSerialBatchByIds(Long[] ids);

    /**
     * 根据id更新指定交易流水信息
     *
     * @param dealSerial 交易流水实体
     * @param id         交易流水id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateDealSerialById(DealSerial dealSerial, Long id);

    /**
     * 添加交易流水
     *
     * @param dealSerial 交易流水实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addDealSerial(DealSerial dealSerial);

}

