package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.SystemLoginLogMapper;
import com.sheep.emo.pojo.SystemLoginLog;
import com.sheep.emo.service.SystemLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作登录日志的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-23
 */

@Service
public class SystemLoginLogServiceImpl implements SystemLoginLogService {

    @Autowired
    private SystemLoginLogMapper systemLoginLogMapper;


    @Override
    public Page<SystemLoginLog> searchOrGetSystemLoginLogList(int current, int size, SystemLoginLog systemLoginLog) {
        Page<SystemLoginLog> page = new Page<>(current, size);
        QueryWrapper<SystemLoginLog> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(systemLoginLog)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(systemLoginLog.getId()) && systemLoginLog.getId() != 0) {
                queryWrapper.eq("id", systemLoginLog.getId());
            }
            if (ObjectUtil.isNotNull(systemLoginLog.getLoginTime())) {
                queryWrapper.eq("login_time", systemLoginLog.getLoginTime());
            }
            if (StrUtil.isNotBlank(systemLoginLog.getUsername())) {
                queryWrapper.eq("username", systemLoginLog.getUsername());
            }
            if (StrUtil.isNotBlank(systemLoginLog.getCompanyName())) {
                queryWrapper.eq("company_name", systemLoginLog.getCompanyName());
            }
            if (StrUtil.isNotBlank(systemLoginLog.getIpAddress())) {
                queryWrapper.eq("ip_address", systemLoginLog.getIpAddress());
            }
            return systemLoginLogMapper.selectPage(page, queryWrapper);
        }
        return systemLoginLogMapper.selectPage(page, null);
    }


    @Override
    public int deleteSystemLoginLogById(Long id) {
        return systemLoginLogMapper.deleteById(id);
    }

    @Override
    public int deleteSystemLoginLogBatchByIds(Long[] ids) {
        return systemLoginLogMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateSystemLoginLogById(SystemLoginLog systemLoginLog, Long id) {
        LambdaQueryWrapper<SystemLoginLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemLoginLog::getId, id);
        return systemLoginLogMapper.update(systemLoginLog, wrapper);
    }

    @Override
    public int addSystemLoginLog(SystemLoginLog systemLoginLog) {
        return systemLoginLogMapper.insert(systemLoginLog);
    }

}

