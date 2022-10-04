package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.CommissionSerial;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.CommissionSerialService;
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
 * 操作佣金流水的控制器
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@RestController
@Api(tags = "操作佣金流水")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class CommissionSerialController {


    @Autowired
    private CommissionSerialService commissionSerialService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemOperateLogService systemOperateLogService;

    /**
     * 分页获得佣金流水列表或者查询并分页获得佣金流水列表
     *
     * @param current          当前页
     * @param size             获取几条
     * @param commissionSerial 需要查询的佣金流水参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得佣金流水列表或查询并分页获得佣金流水列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "commissionSerial", value = "需要查询的佣金流水参数", dataType = "CommissionSerial", dataTypeClass = CommissionSerial.class),
    })
    @PostMapping("/commissionSerials/page/get/{current}/{size}")
    public Result searchOrGetCommissionSerialList(@PathVariable int current,
                                               @PathVariable int size,
                                               @RequestBody(required = false) CommissionSerial commissionSerial) {
        //校验 TODO  如有请写 commissionSerial允许为空 需先判空
        Page<CommissionSerial> pageCommissionSerialList = commissionSerialService.searchOrGetCommissionSerialList(current, size, commissionSerial);
        List<CommissionSerial> records = pageCommissionSerialList.getRecords();
        long total = pageCommissionSerialList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定佣金流水
     *
     * @param id 前端传进的佣金流水id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定佣金流水")
    @GetMapping("/commissionSerial/delete/{id}")
    public Result deleteCommissionSerialById(@PathVariable Long id) {
        int i = commissionSerialService.deleteCommissionSerialById(id);
        if (i > 0) {
            addLog("删除", "佣金流水", "1");
            return Result.ok();
        } else {
            addLog("删除", "佣金流水", "0");
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
     * 根据id批量删除指定佣金流水
     *
     * @param ids 前端传进的佣金流水id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定佣金流水")
    @PostMapping("/commissionSerials/deleteBatch")
    public Result deleteCommissionSerialBatchByIds(@RequestBody Long[] ids) {
        int i = commissionSerialService.deleteCommissionSerialBatchByIds(ids);
        if (i > 0) {
            addLog("批量删除", "佣金流水", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "佣金流水", "0");
            return Result.error();
        }
    }

    /**
     * 根据id更新指定佣金流水信息
     *
     * @param commissionSerial 前端传进的佣金流水实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定佣金流水信息")
    @PostMapping("/commissionSerial/update")
    public Result updateCommissionSerialById(@RequestBody CommissionSerial commissionSerial) {
        commissionSerial.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = commissionSerialService.updateCommissionSerialById(commissionSerial, commissionSerial.getId());
        if (i > 0) {
            addLog("更新", "佣金流水", "1");
            return Result.ok();
        } else {
            addLog("更新", "佣金流水", "0");
            return Result.error();
        }
    }


    /**
     * 添加佣金流水
     *
     * @param commissionSerial 前端传进的佣金流水实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加佣金流水")
    @PostMapping("/commissionSerial/add")
    public Result addCommissionSerial(@RequestBody CommissionSerial commissionSerial) {
        //校验 TODO 如有请写
        int i = commissionSerialService.addCommissionSerial(commissionSerial);
        if (i > 0) {
            addLog("添加", "佣金流水", "1");
            return Result.ok();
        } else {
            addLog("添加", "佣金流水", "0");
            return Result.error();
        }
    }

}
