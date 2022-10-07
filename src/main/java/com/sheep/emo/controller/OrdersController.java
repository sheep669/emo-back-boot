package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.sheep.emo.pojo.Orders;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.OrdersService;
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
 * 操作订单的控制器
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@RestController
@Api(tags = "操作订单")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class OrdersController {


    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemOperateLogService systemOperateLogService;

    /**
     * 分页获得订单列表或者查询并分页获得订单列表
     *
     * @param current 当前页
     * @param size    获取几条
     * @param orders  需要查询的订单参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得订单列表或查询并分页获得订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "orders", value = "需要查询的订单参数", dataType = "Orders", dataTypeClass = Orders.class),
    })
    @PostMapping("/orders/page/get/{current}/{size}")
    public Result searchOrGetOrdersList(@PathVariable int current,
                                     @PathVariable int size,
                                     @RequestBody(required = false) Orders orders) {
        //校验 TODO  如有请写 orders允许为空 需先判空
        Page<Orders> pageOrdersList = ordersService.searchOrGetOrdersList(current, size, orders);
        List<Orders> records = pageOrdersList.getRecords();
        long total = pageOrdersList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定订单
     *
     * @param id 前端传进的订单id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定订单")
    @GetMapping("/order/delete/{id}")
    public Result deleteOrdersById(@PathVariable Long id) {
        int i = ordersService.deleteOrdersById(id);
        if (i > 0) {
            addLog("删除", "订单", "1");
            return Result.ok();
        } else {
            addLog("删除", "订单", "0");
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
     * 根据id批量删除指定订单
     *
     * @param ids 前端传进的订单id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定订单")
    @PostMapping("/orders/deleteBatch")
    public Result deleteOrdersBatchByIds(@RequestBody Long[] ids) {
        int i = ordersService.deleteOrdersBatchByIds(ids);
        if (i > 0) {
            addLog("批量删除", "订单", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "订单", "0");
            return Result.error();
        }
    }

    /**
     * 根据id更新指定订单信息
     *
     * @param orders 前端传进的订单实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定订单信息")
    @PostMapping("/order/update")
    public Result updateOrdersById(@RequestBody Orders orders) {
        orders.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = ordersService.updateOrdersById(orders, orders.getId());
        if (i > 0) {
            addLog("更新", "订单", "1");
            return Result.ok();
        } else {
            addLog("更新", "订单", "0");
            return Result.error();
        }
    }


    /**
     * 添加订单
     *
     * @param orders 前端传进的订单实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加订单")
    @ApiOperationSupport(ignoreParameters = "id")
    @PostMapping("/order/add")
    public Result addOrders(@RequestBody Orders orders) {
        //校验 TODO 如有请写
        int i = ordersService.addOrders(orders);
        if (i > 0) {
            addLog("添加", "订单", "1");
            return Result.ok();
        } else {
            addLog("添加", "订单", "0");
            return Result.error();
        }
    }

}
