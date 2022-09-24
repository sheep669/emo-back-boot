package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.MemberDiscountPrivilege;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.MemberDiscountPrivilegeService;
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
 * 操作会员优惠权限的控制器
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@RestController
@Api(tags = "操作会员优惠权限")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class MemberDiscountPrivilegeController {


    @Autowired
    private MemberDiscountPrivilegeService memberDiscountPrivilegeService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemOperateLogService systemOperateLogService;

    /**
     * 分页获得会员优惠权限列表或者查询并分页获得会员优惠权限列表
     *
     * @param current                 当前页
     * @param size                    获取几条
     * @param memberDiscountPrivilege 需要查询的会员优惠权限参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得会员优惠权限列表或查询并分页获得会员优惠权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "memberDiscountPrivilege", value = "需要查询的会员优惠权限参数", dataType = "MemberDiscountPrivilege", dataTypeClass = MemberDiscountPrivilege.class),
    })
    @PostMapping("/memberDiscountPrivileges/page/get/{current}/{size}")
    public Result searchOrMemberDiscountPrivilegeList(@PathVariable int current,
                                                      @PathVariable int size,
                                                      @RequestBody(required = false) MemberDiscountPrivilege memberDiscountPrivilege) {
        //校验 TODO  如有请写 memberDiscountPrivilege允许为空 需先判空
        Page<MemberDiscountPrivilege> pageMemberDiscountPrivilegeList = memberDiscountPrivilegeService.searchOrGetMemberDiscountPrivilegeList(current, size, memberDiscountPrivilege);
        List<MemberDiscountPrivilege> records = pageMemberDiscountPrivilegeList.getRecords();
        long total = pageMemberDiscountPrivilegeList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定会员优惠权限
     *
     * @param id 前端传进的会员优惠权限id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定会员优惠权限")
    @GetMapping("/memberDiscountPrivilege/delete/{id}")
    public Result deleteMemberDiscountPrivilegeById(@PathVariable Long id) {
        int i = memberDiscountPrivilegeService.deleteMemberDiscountPrivilegeById(id);
        if (i > 0) {
            addLog("删除", "会员优惠权限", "1");
            return Result.ok();
        } else {
            addLog("删除", "会员优惠权限", "0");
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
     * 根据id批量删除指定会员优惠权限
     *
     * @param ids 前端传进的会员优惠权限id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定会员优惠权限")
    @PostMapping("/memberDiscountPrivileges/deleteBatch")
    public Result deleteMemberDiscountPrivilegeBatchByIds(@RequestBody Long[] ids) {
        int i = memberDiscountPrivilegeService.deleteMemberDiscountPrivilegeBatchByIds(ids);
        if (i > 0) {
            addLog("批量删除", "会员优惠权限", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "会员优惠权限", "0");
            return Result.error();
        }
    }

    /**
     * 根据id更新指定会员优惠权限信息
     *
     * @param memberDiscountPrivilege 前端传进的会员优惠权限实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定会员优惠权限信息")
    @PostMapping("/memberDiscountPrivilege/update")
    public Result updateMemberDiscountPrivilegeById(@RequestBody MemberDiscountPrivilege memberDiscountPrivilege) {
        memberDiscountPrivilege.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = memberDiscountPrivilegeService.updateMemberDiscountPrivilegeById(memberDiscountPrivilege, memberDiscountPrivilege.getId());
        if (i > 0) {
            addLog("更新", "会员优惠权限", "1");
            return Result.ok();
        } else {
            addLog("更新", "会员优惠权限", "0");
            return Result.error();
        }
    }


    /**
     * 添加会员优惠权限
     *
     * @param memberDiscountPrivilege 前端传进的会员优惠权限实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加会员优惠权限")
    @PostMapping("/memberDiscountPrivilege/add")
    public Result addMemberDiscountPrivilege(@RequestBody MemberDiscountPrivilege memberDiscountPrivilege) {
        //校验 TODO 如有请写
        int i = memberDiscountPrivilegeService.addMemberDiscountPrivilege(memberDiscountPrivilege);
        if (i > 0) {
            addLog("添加", "会员优惠权限", "1");
            return Result.ok();
        } else {
            addLog("添加", "会员优惠权限", "0");
            return Result.error();
        }
    }

}
