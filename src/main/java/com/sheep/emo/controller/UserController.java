package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

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
        return i > 0 ? Result.ok() : Result.error();
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
        return i > 0 ? Result.ok() : Result.error();
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
        //校验 TODO 如有请写
        int i = userService.updateUserById(user, user.getId());
        return i > 0 ? Result.ok() : Result.error();
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
        //校验 TODO 如有请写
        int i = userService.addUser(user);
        return i > 0 ? Result.ok() : Result.error();
    }

}
