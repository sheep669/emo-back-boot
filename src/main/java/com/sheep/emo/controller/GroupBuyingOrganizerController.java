package com.sheep.emo.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GroupBuyingOrganizer;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.GroupBuyingOrganizerService;
import com.sheep.emo.utils.ValidatorUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : sheep669
 * @description : 团长管理的前端控制器
 * @created at 2022/7/29 11:47
 */
@RestController
@Api(tags = "操作团长")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class GroupBuyingOrganizerController {

    @Autowired
    private GroupBuyingOrganizerService groupBuyingOrganizerService;

    /**
     * 获得团长列表
     *
     * @return java.util.List<com.sheep.emo.pojo.GroupBuyingOrganizer>
     * @author sheep669
     * @created at 2022/8/5 10:03
     */
    @ApiOperation(value = "获得团长列表")
    @GetMapping("/getGroupBuyingOrganizerList")
    public List<GroupBuyingOrganizer> getGroupBuyingOrganizerList() {
        return groupBuyingOrganizerService.getGroupBuyingOrganizerList();
    }

    /**
     * 分页获得团长列表或者查询并分页获得团长列表
     *
     * @param current              当前页
     * @param size                 获取几条
     * @param groupBuyingOrganizer 需要查询的团长参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得团长列表或查询并分页获得团长列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "groupBuyingOrganizer", value = "需要查询的团长参数", dataType = "GroupBuyingOrganizer", dataTypeClass = GroupBuyingOrganizer.class),
    })
    @PostMapping("/groupBuyingOrganizers/page/get/{current}/{size}")
    public Result searchOrGetGroupBuyingOrganizerList(@PathVariable int current,
                                                      @PathVariable int size,
                                                      @RequestBody(required = false) GroupBuyingOrganizer groupBuyingOrganizer) {
        //校验号码是否属于合法格式
        if (ObjectUtil.isNotNull(groupBuyingOrganizer) && StrUtil.isNotBlank(groupBuyingOrganizer.getPhoneNumber())) {
            Map<String, Object> valid = ValidatorUtil.valid(groupBuyingOrganizer);
            if (valid.size() > 0) {
                return Result.error().data(valid);
            }
        }
        Page<GroupBuyingOrganizer> pageGroupBuyingOrganizerList = groupBuyingOrganizerService.searchOrGetGroupBuyingOrganizerList(current, size, groupBuyingOrganizer);
        List<GroupBuyingOrganizer> records = pageGroupBuyingOrganizerList.getRecords();
        long total = pageGroupBuyingOrganizerList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定团长
     *
     * @param id 前端传进的团长id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定团长")
    @GetMapping("/groupBuyingOrganizer/delete/{id}")
    public Result deleteGroupBuyingOrganizerById(@PathVariable Long id) {
        int i = groupBuyingOrganizerService.deleteGroupBuyingOrganizerById(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id批量删除指定团长
     *
     * @param ids 前端传进的团长id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定团长")
    @PostMapping("/groupBuyingOrganizer/deleteBatch")
    public Result deleteGroupBuyingOrganizerBatchByIds(@RequestBody Long[] ids) {
        int i = groupBuyingOrganizerService.deleteGroupBuyingOrganizerBatchByIds(ids);
        return i > 0 ? Result.ok() : Result.error();
    }


    /**
     * 根据id更新指定团长信息
     *
     * @param groupBuyingOrganizer 前端传进的实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定团长信息 -- 参数为空使用null,请不要使用双引号")
    @PostMapping("/groupBuyingOrganizer/update")
    public Result updateGroupBuyingOrganizerById(@RequestBody GroupBuyingOrganizer groupBuyingOrganizer) {
        groupBuyingOrganizer.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验号码是否属于合法格式
        if (StrUtil.isNotBlank(groupBuyingOrganizer.getPhoneNumber())) {
            Map<String, Object> valid = ValidatorUtil.valid(groupBuyingOrganizer);
            if (valid.size() > 0) {
                return Result.error().data(valid);
            }
        }
        int i = groupBuyingOrganizerService.updateGroupBuyingOrganizerById(groupBuyingOrganizer, groupBuyingOrganizer.getId());
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 添加团长
     *
     * @param groupBuyingOrganizer 前端传进的实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加团长 -- 参数为空使用null,请不要使用双引号")
    @PostMapping("/groupBuyingOrganizer/add")
    public Result addGroupBuyingOrganizer(@RequestBody GroupBuyingOrganizer groupBuyingOrganizer) {
        //校验号码是否属于合法格式
        if (StrUtil.isNotBlank(groupBuyingOrganizer.getPhoneNumber())) {
            Map<String, Object> valid = ValidatorUtil.valid(groupBuyingOrganizer);
            if (valid.size() > 0) {
                return Result.error().data(valid);
            }
        }
        int i = groupBuyingOrganizerService.addGroupBuyingOrganizer(groupBuyingOrganizer);
        return i > 0 ? Result.ok() : Result.error();
    }
}

