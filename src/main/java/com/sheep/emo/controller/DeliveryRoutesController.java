package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.DeliveryRoutes;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.DeliveryRoutesService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作配送路线的控制器
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@RestController
@Api(tags = "操作配送路线")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class DeliveryRoutesController {


    @Autowired
    private DeliveryRoutesService deliveryRoutesService;

    /**
     * 分页获得配送路线列表或者查询并分页获得配送路线列表
     *
     * @param current        当前页
     * @param size           获取几条
     * @param deliveryRoutes 需要查询的配送路线参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得配送路线列表或查询并分页获得配送路线列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "deliveryRoutes", value = "需要查询的配送路线参数", dataType = "DeliveryRoutes", dataTypeClass = DeliveryRoutes.class),
    })
    @PostMapping("/deliveryRoutes/page/get/{current}/{size}")
    public Result searchOrDeliveryRoutesList(@PathVariable int current,
                                             @PathVariable int size,
                                             @RequestBody(required = false) DeliveryRoutes deliveryRoutes) {
        //校验 TODO  如有请写 deliveryRoutes允许为空 需先判空
        Page<DeliveryRoutes> pageDeliveryRoutesList = deliveryRoutesService.searchOrGetDeliveryRoutesList(current, size, deliveryRoutes);
        List<DeliveryRoutes> records = pageDeliveryRoutesList.getRecords();
        long total = pageDeliveryRoutesList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定配送路线
     *
     * @param id 前端传进的配送路线id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定配送路线")
    @GetMapping("/deliveryRoutes/delete/{id}")
    public Result deleteDeliveryRoutesById(@PathVariable Long id) {
        int i = deliveryRoutesService.deleteDeliveryRoutesById(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id批量删除指定配送路线
     *
     * @param ids 前端传进的配送路线id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定配送路线")
    @PostMapping("/deliveryRoutes/deleteBatch")
    public Result deleteDeliveryRoutesBatchByIds(@RequestBody Long[] ids) {
        int i = deliveryRoutesService.deleteDeliveryRoutesBatchByIds(ids);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id更新指定配送路线信息
     *
     * @param deliveryRoutes 前端传进的配送路线实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定配送路线信息")
    @PostMapping("/deliveryRoutes/update")
    public Result updateDeliveryRoutesById(@RequestBody DeliveryRoutes deliveryRoutes) {
        deliveryRoutes.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = deliveryRoutesService.updateDeliveryRoutesById(deliveryRoutes, deliveryRoutes.getId());
        return i > 0 ? Result.ok() : Result.error();
    }


    /**
     * 添加配送路线
     *
     * @param deliveryRoutes 前端传进的配送路线实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加配送路线")
    @PostMapping("/deliveryRoutes/add")
    public Result addDeliveryRoutes(@RequestBody DeliveryRoutes deliveryRoutes) {
        //校验 TODO 如有请写
        int i = deliveryRoutesService.addDeliveryRoutes(deliveryRoutes);
        return i > 0 ? Result.ok() : Result.error();
    }

}