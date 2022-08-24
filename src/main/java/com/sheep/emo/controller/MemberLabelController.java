package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.MemberLabel;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.MemberLabelService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作会员标签的控制器
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@RestController
@Api(tags = "操作会员标签")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class MemberLabelController {


    @Autowired
    private MemberLabelService memberLabelService;

    /**
     * 分页获得会员标签列表或者查询并分页获得会员标签列表
     *
     * @param current     当前页
     * @param size        获取几条
     * @param memberLabel 需要查询的会员标签参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得会员标签列表或查询并分页获得会员标签列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "memberLabel", value = "需要查询的会员标签参数", dataType = "MemberLabel", dataTypeClass = MemberLabel.class),
    })
    @PostMapping("/memberLabels/page/get/{current}/{size}")
    public Result searchOrMemberLabelList(@PathVariable int current,
                                          @PathVariable int size,
                                          @RequestBody(required = false) MemberLabel memberLabel) {
        //校验 TODO  如有请写 memberLabel允许为空 需先判空
        Page<MemberLabel> pageMemberLabelList = memberLabelService.searchOrGetMemberLabelList(current, size, memberLabel);
        List<MemberLabel> records = pageMemberLabelList.getRecords();
        long total = pageMemberLabelList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定会员标签
     *
     * @param id 前端传进的会员标签id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定会员标签")
    @GetMapping("/memberLabel/delete/{id}")
    public Result deleteMemberLabelById(@PathVariable Long id) {
        int i = memberLabelService.deleteMemberLabelById(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id批量删除指定会员标签
     *
     * @param ids 前端传进的会员标签id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定会员标签")
    @PostMapping("/memberLabels/deleteBatch")
    public Result deleteMemberLabelBatchByIds(@RequestBody Long[] ids) {
        int i = memberLabelService.deleteMemberLabelBatchByIds(ids);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id更新指定会员标签信息
     *
     * @param memberLabel 前端传进的会员标签实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定会员标签信息")
    @PostMapping("/memberLabel/update")
    public Result updateMemberLabelById(@RequestBody MemberLabel memberLabel) {
        memberLabel.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = memberLabelService.updateMemberLabelById(memberLabel, memberLabel.getId());
        return i > 0 ? Result.ok() : Result.error();
    }


    /**
     * 添加会员标签
     *
     * @param memberLabel 前端传进的会员标签实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加会员标签")
    @PostMapping("/memberLabel/add")
    public Result addMemberLabel(@RequestBody MemberLabel memberLabel) {
        //校验 TODO 如有请写
        int i = memberLabelService.addMemberLabel(memberLabel);
        return i > 0 ? Result.ok() : Result.error();
    }

}
