package com.sheep.emo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.SystemOperateLogService;
import com.sheep.emo.service.UserService;
import com.sheep.emo.utils.RedisUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作用户的控制器
 *
 * @author sheep669
 * @created at 2022-08-23
 */

@RestController
@Api(tags = "操作用户")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemOperateLogService systemOperateLogService;

    /**
     * 分页获得用户列表或者查询并分页获得用户列表
     *
     * @param current 当前页
     * @param size    获取几条
     * @param user    需要查询的用户参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得用户列表或查询并分页获得用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "user", value = "需要查询的用户参数", dataType = "User", dataTypeClass = User.class),
    })
    @PostMapping("/users/page/get/{current}/{size}")
    public Result searchOrUserList(@PathVariable int current,
                                   @PathVariable int size,
                                   @RequestBody(required = false) User user) {
        //校验 TODO  如有请写 user允许为空 需先判空
        Page<User> pageUserList = userService.searchOrGetUserList(current, size, user);
        List<User> records = pageUserList.getRecords();
        long total = pageUserList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定用户
     *
     * @param id 前端传进的用户id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定用户")
    @GetMapping("/user/delete/{id}")
    public Result deleteUserById(@PathVariable Long id) {
        int i = userService.deleteUserById(id);
        if (i > 0) {
            addLog("删除", "用户", "1");
            return Result.ok();
        } else {
            addLog("删除", "用户", "0");
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
     * 根据id批量删除指定用户
     *
     * @param ids 前端传进的用户id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定用户")
    @PostMapping("/users/deleteBatch")
    public Result deleteUserBatchByIds(@RequestBody Long[] ids) {
        int i = userService.deleteUserBatchByIds(ids);
        if (i > 0) {
            addLog("批量删除", "用户", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "用户", "0");
            return Result.error();
        }
    }

    /**
     * 根据id更新指定用户信息
     *
     * @param user 前端传进的用户实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定用户信息")
    @PostMapping("/user/update")
    public Result updateUserById(@RequestBody User user) {
        user.setUpdateTime(new Date(System.currentTimeMillis()));
        //明文加密
        if (StrUtil.isNotBlank(user.getPassword())) {
            String encode = passwordEncoder.encode(user.getPassword());
            user.setPassword(encode);
        }
        //校验 TODO 如有请写
        int i = userService.updateUserById(user, user.getId());
        if (i > 0) {
            addLog("更新", "用户", "1");
            return Result.ok();
        } else {
            addLog("更新", "用户", "0");
            return Result.error();
        }
    }


    /**
     * 添加用户
     *
     * @param user 前端传进的用户实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("/user/add")
    public Result addUser(@RequestBody User user) {
        //明文加密
        if (StrUtil.isNotBlank(user.getPassword())) {
            String encode = passwordEncoder.encode(user.getPassword());
            user.setPassword(encode);
        }
        //校验 TODO 如有请写
        int i = userService.addUser(user);
        if (i > 0) {
            addLog("添加", "用户", "1");
            return Result.ok();
        } else {
            addLog("添加", "用户", "0");
            return Result.error();
        }
    }


    /**
     * 授权为系统普通会员用户
     *
     * @param id 要授权的id
     * @return Result
     * @author sheep669
     * @created at 2022/9/24 22:40
     */
    @ApiOperation(value = "授权为系统普通会员用户")
    @PostMapping("/user/grantVip/{id}")
    public Result grantVip(@PathVariable Long id) {
        int i = userService.grantVip(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 授权为系统超级会员用户
     *
     * @param id 要授权的id
     * @return Result
     * @author sheep669
     * @created at 2022/9/24 22:40
     */
    @ApiOperation(value = "授权为系统超级会员用户")
    @PostMapping("/user/grantSVip/{id}")
    public Result grantSVip(@PathVariable Long id) {
        int i = userService.grantSVip(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 授权为管理员
     *
     * @param id 要授权的id
     * @return Result
     * @author sheep669
     * @created at 2022/9/24 22:40
     */
    @ApiOperation(value = "授权为管理员")
    @PostMapping("/user/grantAdmin/{id}")
    public Result grantAdmin(@PathVariable Long id) {
        int i = userService.grantAdmin(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 授权为团长
     *
     * @param id 要授权的id
     * @return Result
     * @author sheep669
     * @created at 2022/9/24 22:40
     */
    @ApiOperation(value = "授权为团长")
    @PostMapping("/user/grantGroupBuyingOrganizer/{id}")
    public Result grantGroupBuyingOrganizer(@PathVariable Long id) {
        int i = userService.grantGroupBuyingOrganizer(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 授权为商铺会员
     *
     * @param id 要授权的id
     * @return Result
     * @author sheep669
     * @created at 2022/9/24 22:40
     */
    @ApiOperation(value = "授权为商铺会员")
    @PostMapping("/user/grantShopOwners/{id}")
    public Result grantShopOwners(@PathVariable Long id) {
        int i = userService.grantShopOwners(id);
        return i > 0 ? Result.ok() : Result.error();
    }
}
