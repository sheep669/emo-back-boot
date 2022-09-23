package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.AfterSale;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.AfterSaleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作售后的控制器
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@RestController
@Api(tags = "操作售后")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class AfterSaleController {


    @Autowired
    private AfterSaleService afterSaleService;

    /**
     * 分页获得售后列表或者查询并分页获得售后列表
     *
     * @param current   当前页
     * @param size      获取几条
     * @param afterSale 需要查询的售后参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得售后列表或查询并分页获得售后列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "afterSale", value = "需要查询的售后参数", dataType = "AfterSale", dataTypeClass = AfterSale.class),
    })
    @PostMapping("/afterSales/page/get/{current}/{size}")
    public Result searchOrAfterSaleList(@PathVariable int current,
                                        @PathVariable int size,
                                        @RequestBody(required = false) AfterSale afterSale) {
        //校验 TODO  如有请写 afterSale允许为空 需先判空
        Page<AfterSale> pageAfterSaleList = afterSaleService.searchOrGetAfterSaleList(current, size, afterSale);
        List<AfterSale> records = pageAfterSaleList.getRecords();
        long total = pageAfterSaleList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定售后
     *
     * @param id 前端传进的售后id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定售后")
    @GetMapping("/afterSale/delete/{id}")
    public Result deleteAfterSaleById(@PathVariable Long id) {
        int i = afterSaleService.deleteAfterSaleById(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id批量删除指定售后
     *
     * @param ids 前端传进的售后id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定售后")
    @PostMapping("/afterSales/deleteBatch")
    public Result deleteAfterSaleBatchByIds(@RequestBody Long[] ids) {
        int i = afterSaleService.deleteAfterSaleBatchByIds(ids);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id更新指定售后信息
     *
     * @param afterSale 前端传进的售后实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定售后信息")
    @PostMapping("/afterSale/update")
    @PreAuthorize("hasAnyRole('admin')")
    public Result updateAfterSaleById(@RequestBody AfterSale afterSale) {
        afterSale.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = afterSaleService.updateAfterSaleById(afterSale, afterSale.getId());
        return i > 0 ? Result.ok() : Result.error();
    }


    /**
     * 添加售后
     *
     * @param afterSale 前端传进的售后实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加售后")
    @PostMapping("/afterSale/add")
    public Result addAfterSale(@RequestBody AfterSale afterSale) {
        //校验 TODO 如有请写
        int i = afterSaleService.addAfterSale(afterSale);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 确认售后申请审核
     *
     * @param id 售后申请id
     * @return Result
     * @author sheep669
     * @created at 2022/8/15 17:28
     */
    @ApiOperation(value = "确认售后申请审核")
    @PostMapping("/afterSale/audit/confirm/{id}")
    public Result confirmAudit(@PathVariable Long id) {
        int i = afterSaleService.confirmAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 通过审核
     *
     * @param id 售后申请id
     * @return Result
     * @author sheep669
     * @created at 2022/8/15 17:28
     */
    @ApiOperation(value = "通过售后申请审核")
    @PostMapping("/afterSale/audit/approve/{id}")
    public Result approveAudit(@PathVariable Long id) {
        int i = afterSaleService.approveAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 拒绝审核
     *
     * @param id 售后申请id
     * @return Result
     * @author sheep669
     * @created at 2022/8/15 17:28
     */
    @ApiOperation(value = "拒绝售后申请审核")
    @PostMapping("/afterSale/audit/reject/{id}")
    public Result rejectAudit(@PathVariable Long id) {
        int i = afterSaleService.rejectAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 重新审核
     *
     * @param id 售后申请id
     * @return Result
     * @author sheep669
     * @created at 2022/8/20 10:05
     */
    @ApiOperation(value = "重新审核")
    @PostMapping("/afterSale/audit/reAudit/{id}")
    public Result reAudit(@PathVariable Long id) {
        int i = afterSaleService.reAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }

}
