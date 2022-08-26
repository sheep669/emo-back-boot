package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GroupBuyingOrganizerClear;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.GroupBuyingOrganizerClearService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作团长结算的控制器
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@RestController
@Api(tags = "操作团长结算")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class GroupBuyingOrganizerClearController {


    @Autowired
    private GroupBuyingOrganizerClearService groupBuyingOrganizerClearService;

    /**
     * 分页获得团长结算列表或者查询并分页获得团长结算列表
     *
     * @param current                   当前页
     * @param size                      获取几条
     * @param groupBuyingOrganizerClear 需要查询的团长结算参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得团长结算列表或查询并分页获得团长结算列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "groupBuyingOrganizerClear", value = "需要查询的团长结算参数", dataType = "GroupBuyingOrganizerClear", dataTypeClass = GroupBuyingOrganizerClear.class),
    })
    @PostMapping("/groupBuyingOrganizerClears/page/get/{current}/{size}")
    public Result searchOrGroupBuyingOrganizerClearList(@PathVariable int current,
                                                        @PathVariable int size,
                                                        @RequestBody(required = false) GroupBuyingOrganizerClear groupBuyingOrganizerClear) {
        //校验 TODO  如有请写 groupBuyingOrganizerClear允许为空 需先判空
        Page<GroupBuyingOrganizerClear> pageGroupBuyingOrganizerClearList = groupBuyingOrganizerClearService.searchOrGetGroupBuyingOrganizerClearList(current, size, groupBuyingOrganizerClear);
        List<GroupBuyingOrganizerClear> records = pageGroupBuyingOrganizerClearList.getRecords();
        long total = pageGroupBuyingOrganizerClearList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定团长结算
     *
     * @param id 前端传进的团长结算id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定团长结算")
    @GetMapping("/groupBuyingOrganizerClear/delete/{id}")
    public Result deleteGroupBuyingOrganizerClearById(@PathVariable Long id) {
        int i = groupBuyingOrganizerClearService.deleteGroupBuyingOrganizerClearById(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id批量删除指定团长结算
     *
     * @param ids 前端传进的团长结算id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定团长结算")
    @PostMapping("/groupBuyingOrganizerClears/deleteBatch")
    public Result deleteGroupBuyingOrganizerClearBatchByIds(@RequestBody Long[] ids) {
        int i = groupBuyingOrganizerClearService.deleteGroupBuyingOrganizerClearBatchByIds(ids);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id更新指定团长结算信息
     *
     * @param groupBuyingOrganizerClear 前端传进的团长结算实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定团长结算信息")
    @PostMapping("/groupBuyingOrganizerClear/update")
    public Result updateGroupBuyingOrganizerClearById(@RequestBody GroupBuyingOrganizerClear groupBuyingOrganizerClear) {
        groupBuyingOrganizerClear.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = groupBuyingOrganizerClearService.updateGroupBuyingOrganizerClearById(groupBuyingOrganizerClear, groupBuyingOrganizerClear.getId());
        return i > 0 ? Result.ok() : Result.error();
    }


    /**
     * 添加团长结算
     *
     * @param groupBuyingOrganizerClear 前端传进的团长结算实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加团长结算")
    @PostMapping("/groupBuyingOrganizerClear/add")
    public Result addGroupBuyingOrganizerClear(@RequestBody GroupBuyingOrganizerClear groupBuyingOrganizerClear) {
        //校验 TODO 如有请写
        int i = groupBuyingOrganizerClearService.addGroupBuyingOrganizerClear(groupBuyingOrganizerClear);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 团长结算
     *
     * @param id 团长id
     * @return Result
     * @author sheep669
     * @created at 2022/8/20 10:05
     */
    @ApiOperation(value = "团长结算")
    @PostMapping("/groupBuyingOrganizer/doPayCalculation/{id}")
    public Result doPayCalculation(@PathVariable Long id) {
        int i = groupBuyingOrganizerClearService.doPayCalculation(id);
        return i > 0 ? Result.ok() : Result.error();
    }

}
