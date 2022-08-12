package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GroupBuyingOrganizerAudit;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.GroupBuyingOrganizerAuditService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author sheep669
 * @created at 2022-08-12
 */

@RestController
@Api(tags = "操作团长审核")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class GroupBuyingOrganizerAuditController {


    @Autowired
    private GroupBuyingOrganizerAuditService groupBuyingOrganizerAuditService;

    /**
     * 分页获得团长审核列表或者查询并分页获得团长审核列表
     *
     * @param current                   当前页
     * @param size                      获取几条
     * @param groupBuyingOrganizerAudit 需要查询的团长审核参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得团长审核列表或查询并分页获得团长审核列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "groupBuyingOrganizerAudit", value = "需要查询的团长审核参数", dataType = "GroupBuyingOrganizerAudit", dataTypeClass = GroupBuyingOrganizerAudit.class),
    })
    @PostMapping("/groupBuyingOrganizerAudits/page/get/{current}/{size}")
    public Result searchOrGroupBuyingOrganizerAuditList(@PathVariable int current,
                                                        @PathVariable int size,
                                                        @RequestBody(required = false) GroupBuyingOrganizerAudit groupBuyingOrganizerAudit) {
        //校验 TODO  如有请写
        Page<GroupBuyingOrganizerAudit> pageGroupBuyingOrganizerAuditList = groupBuyingOrganizerAuditService.searchOrGetGroupBuyingOrganizerAuditList(current, size, groupBuyingOrganizerAudit);
        List<GroupBuyingOrganizerAudit> records = pageGroupBuyingOrganizerAuditList.getRecords();
        long total = pageGroupBuyingOrganizerAuditList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定团长审核
     *
     * @param id 前端传进的团长审核id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定团长审核")
    @GetMapping("/groupBuyingOrganizerAudit/delete/{id}")
    public Result deleteGroupBuyingOrganizerAuditById(@PathVariable Long id) {
        int i = groupBuyingOrganizerAuditService.deleteGroupBuyingOrganizerAuditById(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id批量删除指定团长审核
     *
     * @param ids 前端传进的团长审核id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定团长审核")
    @PostMapping("/groupBuyingOrganizerAudit/deleteBatch")
    public Result deleteGroupBuyingOrganizerAuditBatchByIds(@RequestBody Long[] ids) {
        int i = groupBuyingOrganizerAuditService.deleteGroupBuyingOrganizerAuditBatchByIds(ids);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id更新指定团长审核信息
     *
     * @param groupBuyingOrganizerAudit 前端传进的团长审核实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定团长审核信息 -- 参数为空使用null,请不要使用双引号")
    @PostMapping("/groupBuyingOrganizerAudit/update")
    public Result updateGroupBuyingOrganizerAuditById(@RequestBody GroupBuyingOrganizerAudit groupBuyingOrganizerAudit) {
        groupBuyingOrganizerAudit.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = groupBuyingOrganizerAuditService.updateGroupBuyingOrganizerAuditById(groupBuyingOrganizerAudit, groupBuyingOrganizerAudit.getId());
        return i > 0 ? Result.ok() : Result.error();
    }


    /**
     * 添加团长审核
     *
     * @param groupBuyingOrganizerAudit 前端传进的团长审核实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加团长审核 -- 参数为空使用null,请不要使用双引号")
    @PostMapping("/groupBuyingOrganizerAudit/add")
    public Result addGroupBuyingOrganizerAudit(@RequestBody GroupBuyingOrganizerAudit groupBuyingOrganizerAudit) {
        //校验 TODO 如有请写
        int i = groupBuyingOrganizerAuditService.addGroupBuyingOrganizerAudit(groupBuyingOrganizerAudit);
        return i > 0 ? Result.ok() : Result.error();
    }

}
