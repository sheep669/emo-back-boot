package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.DealSerial;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.DealSerialService;
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
 * 操作交易流水的控制器
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@RestController
@Api(tags = "操作交易流水")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class DealSerialController {


    @Autowired
    private DealSerialService dealSerialService;
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemOperateLogService systemOperateLogService;

    /**
     * 分页获得交易流水列表或者查询并分页获得交易流水列表
     *
     * @param current    当前页
     * @param size       获取几条
     * @param dealSerial 需要查询的交易流水参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得交易流水列表或查询并分页获得交易流水列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "dealSerial", value = "需要查询的交易流水参数", dataType = "DealSerial", dataTypeClass = DealSerial.class),
    })
    @PostMapping("/dealSerials/page/get/{current}/{size}")
    public Result searchOrGetDealSerialList(@PathVariable int current,
                                         @PathVariable int size,
                                         @RequestBody(required = false) DealSerial dealSerial) {
        //校验 TODO  如有请写 dealSerial允许为空 需先判空
        Page<DealSerial> pageDealSerialList = dealSerialService.searchOrGetDealSerialList(current, size, dealSerial);
        List<DealSerial> records = pageDealSerialList.getRecords();
        long total = pageDealSerialList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定交易流水
     *
     * @param id 前端传进的交易流水id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定交易流水")
    @GetMapping("/dealSerial/delete/{id}")
    public Result deleteDealSerialById(@PathVariable Long id) {
        int i = dealSerialService.deleteDealSerialById(id);
        if (i > 0) {
            addLog("删除", "交易流水", "1");
            return Result.ok();
        } else {
            addLog("删除", "交易流水", "0");
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
     * 根据id批量删除指定交易流水
     *
     * @param ids 前端传进的交易流水id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定交易流水")
    @PostMapping("/dealSerials/deleteBatch")
    public Result deleteDealSerialBatchByIds(@RequestBody Long[] ids) {
        int i = dealSerialService.deleteDealSerialBatchByIds(ids);
        if (i > 0) {
            addLog("批量删除", "交易流水", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "交易流水", "0");
            return Result.error();
        }
    }

    /**
     * 根据id更新指定交易流水信息
     *
     * @param dealSerial 前端传进的交易流水实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定交易流水信息")
    @PostMapping("/dealSerial/update")
    public Result updateDealSerialById(@RequestBody DealSerial dealSerial) {
        dealSerial.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = dealSerialService.updateDealSerialById(dealSerial, dealSerial.getId());
        if (i > 0) {
            addLog("更新", "交易流水", "1");
            return Result.ok();
        } else {
            addLog("更新", "交易流水", "0");
            return Result.error();
        }
    }


    /**
     * 添加交易流水
     *
     * @param dealSerial 前端传进的交易流水实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加交易流水")
    @PostMapping("/dealSerial/add")
    public Result addDealSerial(@RequestBody DealSerial dealSerial) {
        //校验 TODO 如有请写
        int i = dealSerialService.addDealSerial(dealSerial);
        if (i > 0) {
            addLog("添加", "交易流水", "1");
            return Result.ok();
        } else {
            addLog("添加", "交易流水", "0");
            return Result.error();
        }
    }

}
