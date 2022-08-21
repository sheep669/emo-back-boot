package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GroupBuyingOrganizerWithdrawalInformation;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.GroupBuyingOrganizerWithdrawalInformationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作团长提现信息的控制器
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@RestController
@Api(tags = "操作团长提现信息")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class GroupBuyingOrganizerWithdrawalInformationController {


    @Autowired
    private GroupBuyingOrganizerWithdrawalInformationService groupBuyingOrganizerWithdrawalInformationService;

    /**
     * 分页获得团长提现信息列表或者查询并分页获得团长提现信息列表
     *
     * @param current                                   当前页
     * @param size                                      获取几条
     * @param groupBuyingOrganizerWithdrawalInformation 需要查询的团长提现信息参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得团长提现信息列表或查询并分页获得团长提现信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "groupBuyingOrganizerWithdrawalInformation", value = "需要查询的团长提现信息参数", dataType = "GroupBuyingOrganizerWithdrawalInformation", dataTypeClass = GroupBuyingOrganizerWithdrawalInformation.class),
    })
    @PostMapping("/groupBuyingOrganizerWithdrawalInformation/page/get/{current}/{size}")
    public Result searchOrGroupBuyingOrganizerWithdrawalInformationList(@PathVariable int current,
                                                                        @PathVariable int size,
                                                                        @RequestBody(required = false) GroupBuyingOrganizerWithdrawalInformation groupBuyingOrganizerWithdrawalInformation) {
        //校验 TODO  如有请写 groupBuyingOrganizerWithdrawalInformation允许为空 需先判空
        Page<GroupBuyingOrganizerWithdrawalInformation> pageGroupBuyingOrganizerWithdrawalInformationList = groupBuyingOrganizerWithdrawalInformationService.searchOrGetGroupBuyingOrganizerWithdrawalInformationList(current, size, groupBuyingOrganizerWithdrawalInformation);
        List<GroupBuyingOrganizerWithdrawalInformation> records = pageGroupBuyingOrganizerWithdrawalInformationList.getRecords();
        long total = pageGroupBuyingOrganizerWithdrawalInformationList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定团长提现信息
     *
     * @param id 前端传进的团长提现信息id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定团长提现信息")
    @GetMapping("/groupBuyingOrganizerWithdrawalInformation/delete/{id}")
    public Result deleteGroupBuyingOrganizerWithdrawalInformationById(@PathVariable Long id) {
        int i = groupBuyingOrganizerWithdrawalInformationService.deleteGroupBuyingOrganizerWithdrawalInformationById(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id批量删除指定团长提现信息
     *
     * @param ids 前端传进的团长提现信息id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定团长提现信息")
    @PostMapping("/groupBuyingOrganizerWithdrawalInformation/deleteBatch")
    public Result deleteGroupBuyingOrganizerWithdrawalInformationBatchByIds(@RequestBody Long[] ids) {
        int i = groupBuyingOrganizerWithdrawalInformationService.deleteGroupBuyingOrganizerWithdrawalInformationBatchByIds(ids);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id更新指定团长提现信息信息
     *
     * @param groupBuyingOrganizerWithdrawalInformation 前端传进的团长提现信息实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定团长提现信息")
    @PostMapping("/groupBuyingOrganizerWithdrawalInformation/update")
    public Result updateGroupBuyingOrganizerWithdrawalInformationById(@RequestBody GroupBuyingOrganizerWithdrawalInformation groupBuyingOrganizerWithdrawalInformation) {
        groupBuyingOrganizerWithdrawalInformation.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = groupBuyingOrganizerWithdrawalInformationService.updateGroupBuyingOrganizerWithdrawalInformationById(groupBuyingOrganizerWithdrawalInformation, groupBuyingOrganizerWithdrawalInformation.getId());
        return i > 0 ? Result.ok() : Result.error();
    }


    /**
     * 添加团长提现信息
     *
     * @param groupBuyingOrganizerWithdrawalInformation 前端传进的团长提现信息实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加团长提现信息")
    @PostMapping("/groupBuyingOrganizerWithdrawalInformation/add")
    public Result addGroupBuyingOrganizerWithdrawalInformation(@RequestBody GroupBuyingOrganizerWithdrawalInformation groupBuyingOrganizerWithdrawalInformation) {
        //校验 TODO 如有请写
        int i = groupBuyingOrganizerWithdrawalInformationService.addGroupBuyingOrganizerWithdrawalInformation(groupBuyingOrganizerWithdrawalInformation);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 确认团长提现信息审核
     *
     * @param id 团长提现信息id
     * @return Result
     * @author sheep669
     * @created at 2022/8/15 17:28
     */
    @ApiOperation(value = "确认团长提现信息审核")
    @PostMapping("/groupBuyingOrganizerWithdrawalInformation/audit/confirm/{id}")
    public Result confirmAudit(@PathVariable Long id) {
        int i = groupBuyingOrganizerWithdrawalInformationService.confirmAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 通过审核
     *
     * @param id 团长提现信息id
     * @return Result
     * @author sheep669
     * @created at 2022/8/15 17:28
     */
    @ApiOperation(value = "通过团长提现信息审核")
    @PostMapping("/groupBuyingOrganizerWithdrawalInformation/audit/approve/{id}")
    public Result approveAudit(@PathVariable Long id) {
        int i = groupBuyingOrganizerWithdrawalInformationService.approveAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 拒绝审核
     *
     * @param id 团长提现信息id
     * @return Result
     * @author sheep669
     * @created at 2022/8/15 17:28
     */
    @ApiOperation(value = "拒绝团长提现信息审核")
    @PostMapping("/groupBuyingOrganizerWithdrawalInformation/audit/reject/{id}")
    public Result rejectAudit(@PathVariable Long id) {
        int i = groupBuyingOrganizerWithdrawalInformationService.rejectAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 重新审核
     *
     * @param id 团长提现信息id
     * @return Result
     * @author sheep669
     * @created at 2022/8/20 10:05
     */
    @ApiOperation(value = "重新审核")
    @PostMapping("/groupBuyingOrganizerWithdrawalInformation/audit/reAudit/{id}")
    public Result reAudit(@PathVariable Long id) {
        int i = groupBuyingOrganizerWithdrawalInformationService.reAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }

}
