package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.SystemLoginLog;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.SystemLoginLogService;
import com.sheep.emo.service.SystemOperateLogService;
import com.sheep.emo.service.UserService;
import com.sheep.emo.utils.RedisUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作登录日志的控制器
 *
 * @author sheep669
 * @created at 2022-08-23
 */

@RestController
@Api(tags = "操作登录日志")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class SystemLoginLogController {


    @Autowired
    private SystemLoginLogService systemLoginLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemOperateLogService systemOperateLogService;

    /**
     * 分页获得登录日志列表或者查询并分页获得登录日志列表
     *
     * @param current        当前页
     * @param size           获取几条
     * @param systemLoginLog 需要查询的登录日志参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得登录日志列表或查询并分页获得登录日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "systemLoginLog", value = "需要查询的登录日志参数", dataType = "SystemLoginLog", dataTypeClass = SystemLoginLog.class),
    })
    @PostMapping("/systemLoginLogs/page/get/{current}/{size}")
    public Result searchOrSystemLoginLogList(@PathVariable int current,
                                             @PathVariable int size,
                                             @RequestBody(required = false) SystemLoginLog systemLoginLog) {
        //校验 TODO  如有请写 systemLoginLog允许为空 需先判空
        Page<SystemLoginLog> pageSystemLoginLogList = systemLoginLogService.searchOrGetSystemLoginLogList(current, size, systemLoginLog);
        List<SystemLoginLog> records = pageSystemLoginLogList.getRecords();
        long total = pageSystemLoginLogList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定登录日志
     *
     * @param id 前端传进的登录日志id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定登录日志")
    @GetMapping("/systemLoginLog/delete/{id}")
    public Result deleteSystemLoginLogById(@PathVariable Long id) {
        int i = systemLoginLogService.deleteSystemLoginLogById(id);
        if (i > 0) {
            addLog("删除", "登录日志", "1");
            return Result.ok();
        } else {
            addLog("删除", "登录日志", "0");
            return Result.error();
        }
    }

    private void addLog(String operateLog, String operateModule, String operateResult) {
        User user = userService.findUserByUsername((String) redisUtil.getValueByKey("username"));
        SystemOperateLog systemOperateLog = new SystemOperateLog();
        systemOperateLog.setOperatorName(user.getUsername());
        systemOperateLog.setOperateTime(new Date(System.currentTimeMillis()));
        systemOperateLog.setOperateLog(operateLog);
        systemOperateLog.setOperateModule(operateModule);
        systemOperateLog.setOperateResult(operateResult);
        systemOperateLog.setOperatePhoneNumber(user.getPhoneNumber());
        systemOperateLog.setOperatorAuthority(user.getRole());
        systemOperateLog.setCompanyName(user.getUsername());
        systemOperateLogService.addSystemOperateLog(systemOperateLog);
    }

    /**
     * 根据id批量删除指定登录日志
     *
     * @param ids 前端传进的登录日志id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定登录日志")
    @PostMapping("/systemLoginLogs/deleteBatch")
    public Result deleteSystemLoginLogBatchByIds(@RequestBody Long[] ids) {
        int i = systemLoginLogService.deleteSystemLoginLogBatchByIds(ids);
        if (i > 0) {
            addLog("批量删除", "登录日志", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "登录日志", "0");
            return Result.error();
        }
    }

    /**
     * 根据id更新指定登录日志信息
     *
     * @param systemLoginLog 前端传进的登录日志实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定登录日志信息")
    @PostMapping("/systemLoginLog/update")
    public Result updateSystemLoginLogById(@RequestBody SystemLoginLog systemLoginLog) {
        systemLoginLog.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = systemLoginLogService.updateSystemLoginLogById(systemLoginLog, systemLoginLog.getId());
        if (i > 0) {
            addLog("更新", "登录日志", "1");
            return Result.ok();
        } else {
            addLog("更新", "登录日志", "0");
            return Result.error();
        }
    }


    /**
     * 添加登录日志
     *
     * @param systemLoginLog 前端传进的登录日志实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加登录日志")
    @PostMapping("/systemLoginLog/add")
    public Result addSystemLoginLog(@RequestBody SystemLoginLog systemLoginLog) {
        //校验 TODO 如有请写
        int i = systemLoginLogService.addSystemLoginLog(systemLoginLog);
        if (i > 0) {
            addLog("添加", "登录日志", "1");
            return Result.ok();
        } else {
            addLog("添加", "登录日志", "0");
            return Result.error();
        }
    }

}
