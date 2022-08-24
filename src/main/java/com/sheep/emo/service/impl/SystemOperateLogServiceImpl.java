package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.SystemOperateLogMapper;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.service.SystemOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作操作日志的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-23
 */

@Service
public class SystemOperateLogServiceImpl implements SystemOperateLogService {

    @Autowired
    private SystemOperateLogMapper systemOperateLogMapper;


    @Override
    public Page<SystemOperateLog> searchOrGetSystemOperateLogList(int current, int size, SystemOperateLog systemOperateLog) {
        Page<SystemOperateLog> page = new Page<>(current, size);
        QueryWrapper<SystemOperateLog> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(systemOperateLog)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(systemOperateLog.getId()) && systemOperateLog.getId() != 0) {
                queryWrapper.eq("id", systemOperateLog.getId());
            }
            if (ObjectUtil.isNotNull(systemOperateLog.getOperateTime())) {
                queryWrapper.eq("operate_time", systemOperateLog.getOperateTime());
            }
            if (StrUtil.isNotBlank(systemOperateLog.getOperateLog())) {
                queryWrapper.eq("operate_log", systemOperateLog.getOperateLog());
            }
            if (StrUtil.isNotBlank(systemOperateLog.getOperateModule())) {
                queryWrapper.eq("operate_module", systemOperateLog.getOperateModule());
            }
            if (StrUtil.isNotBlank(systemOperateLog.getOperatePhoneNumber())) {
                queryWrapper.eq("operate_phone_number", systemOperateLog.getOperatePhoneNumber());
            }
            if (StrUtil.isNotBlank(systemOperateLog.getOperatorAuthority())) {
                queryWrapper.eq("operator_authority", systemOperateLog.getOperatorAuthority());
            }
            if (StrUtil.isNotBlank(systemOperateLog.getOperatorName())) {
                queryWrapper.eq("operator_name", systemOperateLog.getOperatorName());
            }
            if (StrUtil.isNotBlank(systemOperateLog.getCompanyName())) {
                queryWrapper.eq("company_name", systemOperateLog.getCompanyName());
            }
            if (StrUtil.isNotBlank(systemOperateLog.getOperateResult())) {
                queryWrapper.eq("operate_result", systemOperateLog.getOperateResult());
            }
            return systemOperateLogMapper.selectPage(page, queryWrapper);
        }
        return systemOperateLogMapper.selectPage(page, null);
    }


    @Override
    public int deleteSystemOperateLogById(Long id) {
        return systemOperateLogMapper.deleteById(id);
    }

    @Override
    public int deleteSystemOperateLogBatchByIds(Long[] ids) {
        return systemOperateLogMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateSystemOperateLogById(SystemOperateLog systemOperateLog, Long id) {
        LambdaQueryWrapper<SystemOperateLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemOperateLog::getId, id);
        return systemOperateLogMapper.update(systemOperateLog, wrapper);
    }

    @Override
    public int addSystemOperateLog(SystemOperateLog systemOperateLog) {
        return systemOperateLogMapper.insert(systemOperateLog);
    }

}

