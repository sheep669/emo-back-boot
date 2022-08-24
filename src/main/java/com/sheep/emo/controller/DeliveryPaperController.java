package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.DeliveryPaper;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.DeliveryPaperService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作配送单管理的控制器
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@RestController
@Api(tags = "操作配送单管理")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class DeliveryPaperController {


    @Autowired
    private DeliveryPaperService deliveryPaperService;

    /**
     * 分页获得配送单管理列表或者查询并分页获得配送单管理列表
     *
     * @param current       当前页
     * @param size          获取几条
     * @param deliveryPaper 需要查询的配送单管理参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得配送单管理列表或查询并分页获得配送单管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "deliveryPaper", value = "需要查询的配送单管理参数", dataType = "DeliveryPaper", dataTypeClass = DeliveryPaper.class),
    })
    @PostMapping("/deliveryPapers/page/get/{current}/{size}")
    public Result searchOrDeliveryPaperList(@PathVariable int current,
                                            @PathVariable int size,
                                            @RequestBody(required = false) DeliveryPaper deliveryPaper) {
        //校验 TODO  如有请写 deliveryPaper允许为空 需先判空
        Page<DeliveryPaper> pageDeliveryPaperList = deliveryPaperService.searchOrGetDeliveryPaperList(current, size, deliveryPaper);
        List<DeliveryPaper> records = pageDeliveryPaperList.getRecords();
        long total = pageDeliveryPaperList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定配送单管理
     *
     * @param id 前端传进的配送单管理id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定配送单管理")
    @GetMapping("/deliveryPaper/delete/{id}")
    public Result deleteDeliveryPaperById(@PathVariable Long id) {
        int i = deliveryPaperService.deleteDeliveryPaperById(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id批量删除指定配送单管理
     *
     * @param ids 前端传进的配送单管理id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定配送单管理")
    @PostMapping("/deliveryPapers/deleteBatch")
    public Result deleteDeliveryPaperBatchByIds(@RequestBody Long[] ids) {
        int i = deliveryPaperService.deleteDeliveryPaperBatchByIds(ids);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id更新指定配送单管理信息
     *
     * @param deliveryPaper 前端传进的配送单管理实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定配送单管理信息")
    @PostMapping("/deliveryPaper/update")
    public Result updateDeliveryPaperById(@RequestBody DeliveryPaper deliveryPaper) {
        deliveryPaper.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = deliveryPaperService.updateDeliveryPaperById(deliveryPaper, deliveryPaper.getId());
        return i > 0 ? Result.ok() : Result.error();
    }


    /**
     * 添加配送单管理
     *
     * @param deliveryPaper 前端传进的配送单管理实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加配送单管理")
    @PostMapping("/deliveryPaper/add")
    public Result addDeliveryPaper(@RequestBody DeliveryPaper deliveryPaper) {
        //校验 TODO 如有请写
        int i = deliveryPaperService.addDeliveryPaper(deliveryPaper);
        return i > 0 ? Result.ok() : Result.error();
    }

}
