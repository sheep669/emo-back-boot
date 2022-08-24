package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.SystemOperateLog;
import org.springframework.stereotype.Service;

/**
 * 操作操作日志的服务接口
 *
 * @author sheep669
 * @created at 2022-08-23
 */

@Service
public interface SystemOperateLogService {


    /**
     * 分页获得操作日志列表或者查询并分页获得操作日志列表
     *
     * @param current          当前第几页
     * @param size             每页条数
     * @param systemOperateLog 查询条件
     * @return Page<SystemOperateLog>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<SystemOperateLog> searchOrGetSystemOperateLogList(int current, int size, SystemOperateLog systemOperateLog);

    /**
     * 根据id删除指定操作日志
     *
     * @param id 操作日志id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteSystemOperateLogById(Long id);

    /**
     * 批量删除操作日志
     *
     * @param ids 操作日志id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteSystemOperateLogBatchByIds(Long[] ids);

    /**
     * 根据id更新指定操作日志信息
     *
     * @param systemOperateLog 操作日志实体
     * @param id               操作日志id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateSystemOperateLogById(SystemOperateLog systemOperateLog, Long id);

    /**
     * 添加操作日志
     *
     * @param systemOperateLog 操作日志实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addSystemOperateLog(SystemOperateLog systemOperateLog);

}

