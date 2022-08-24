package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.SystemLoginLog;
import org.springframework.stereotype.Service;

/**
 * 操作登录日志的服务接口
 *
 * @author sheep669
 * @created at 2022-08-23
 */

@Service
public interface SystemLoginLogService {


    /**
     * 分页获得登录日志列表或者查询并分页获得登录日志列表
     *
     * @param current        当前第几页
     * @param size           每页条数
     * @param systemLoginLog 查询条件
     * @return Page<SystemLoginLog>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<SystemLoginLog> searchOrGetSystemLoginLogList(int current, int size, SystemLoginLog systemLoginLog);

    /**
     * 根据id删除指定登录日志
     *
     * @param id 登录日志id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteSystemLoginLogById(Long id);

    /**
     * 批量删除登录日志
     *
     * @param ids 登录日志id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteSystemLoginLogBatchByIds(Long[] ids);

    /**
     * 根据id更新指定登录日志信息
     *
     * @param systemLoginLog 登录日志实体
     * @param id             登录日志id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateSystemLoginLogById(SystemLoginLog systemLoginLog, Long id);

    /**
     * 添加登录日志
     *
     * @param systemLoginLog 登录日志实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addSystemLoginLog(SystemLoginLog systemLoginLog);

}

