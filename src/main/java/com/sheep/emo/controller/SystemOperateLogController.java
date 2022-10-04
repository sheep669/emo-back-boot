package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
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
 * 操作操作日志的控制器
 *
 * @author sheep669
 * @created at 2022-08-23
 */

@RestController
@Api(tags = "操作操作日志")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class SystemOperateLogController {


    @Autowired
    private SystemOperateLogService systemOperateLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页获得操作日志列表或者查询并分页获得操作日志列表
     *
     * @param current          当前页
     * @param size             获取几条
     * @param systemOperateLog 需要查询的操作日志参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得操作日志列表或查询并分页获得操作日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "systemOperateLog", value = "需要查询的操作日志参数", dataType = "SystemOperateLog", dataTypeClass = SystemOperateLog.class),
    })
    @PostMapping("/systemOperateLogs/page/get/{current}/{size}")
    public Result searchOrGetSystemOperateLogList(@PathVariable int current,
                                               @PathVariable int size,
                                               @RequestBody(required = false) SystemOperateLog systemOperateLog) {
        //校验 TODO  如有请写 systemOperateLog允许为空 需先判空
        Page<SystemOperateLog> pageSystemOperateLogList = systemOperateLogService.searchOrGetSystemOperateLogList(current, size, systemOperateLog);
        List<SystemOperateLog> records = pageSystemOperateLogList.getRecords();
        long total = pageSystemOperateLogList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定操作日志
     *
     * @param id 前端传进的操作日志id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定操作日志")
    @GetMapping("/systemOperateLog/delete/{id}")
    public Result deleteSystemOperateLogById(@PathVariable Long id) {
        int i = systemOperateLogService.deleteSystemOperateLogById(id);
        if (i > 0) {
            addLog("删除", "操作日志", "1");
            return Result.ok();
        } else {
            addLog("删除", "操作日志", "0");
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
     * 根据id批量删除指定操作日志
     *
     * @param ids 前端传进的操作日志id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定操作日志")
    @PostMapping("/systemOperateLogs/deleteBatch")
    public Result deleteSystemOperateLogBatchByIds(@RequestBody Long[] ids) {
        int i = systemOperateLogService.deleteSystemOperateLogBatchByIds(ids);
        if (i > 0) {
            addLog("批量删除", "操作日志", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "操作日志", "0");
            return Result.error();
        }
    }

    /**
     * 根据id更新指定操作日志信息
     *
     * @param systemOperateLog 前端传进的操作日志实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定操作日志信息")
    @PostMapping("/systemOperateLog/update")
    public Result updateSystemOperateLogById(@RequestBody SystemOperateLog systemOperateLog) {
        systemOperateLog.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = systemOperateLogService.updateSystemOperateLogById(systemOperateLog, systemOperateLog.getId());
        return i > 0 ? Result.ok() : Result.error();
    }


    /**
     * 添加操作日志
     *
     * @param systemOperateLog 前端传进的操作日志实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加操作日志")
    @PostMapping("/systemOperateLog/add")
    public Result addSystemOperateLog(@RequestBody SystemOperateLog systemOperateLog) {
        //校验 TODO 如有请写
        int i = systemOperateLogService.addSystemOperateLog(systemOperateLog);
        if (i > 0) {
            addLog("添加", "操作日志", "1");
            return Result.ok();
        } else {
            addLog("添加", "操作日志", "0");
            return Result.error();
        }
    }

}
