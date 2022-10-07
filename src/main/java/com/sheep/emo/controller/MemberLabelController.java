package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.sheep.emo.pojo.MemberLabel;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.MemberLabelService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemOperateLogService systemOperateLogService;

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
    public Result searchOrGetMemberLabelList(@PathVariable int current,
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
        if (i > 0) {
            addLog("删除", "会员标签", "1");
            return Result.ok();
        } else {
            addLog("删除", "会员标签", "0");
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
        if (i > 0) {
            addLog("批量删除", "会员标签", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "会员标签", "0");
            return Result.error();
        }
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
        if (i > 0) {
            addLog("更新", "会员标签", "1");
            return Result.ok();
        } else {
            addLog("更新", "会员标签", "0");
            return Result.error();
        }
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
    @ApiOperationSupport(ignoreParameters = "id")
    @PostMapping("/memberLabel/add")
    public Result addMemberLabel(@RequestBody MemberLabel memberLabel) {
        //校验 TODO 如有请写
        int i = memberLabelService.addMemberLabel(memberLabel);
        if (i > 0) {
            addLog("添加", "会员标签", "1");
            return Result.ok();
        } else {
            addLog("添加", "会员标签", "0");
            return Result.error();
        }
    }

}
