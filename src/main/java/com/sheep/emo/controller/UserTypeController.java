package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.UserType;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.UserTypeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作用户类型的控制器
 *
 * @author sheep669
 * @created at 2022-08-23
 */

@RestController
@Api(tags = "操作用户类型")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class UserTypeController {


    @Autowired
    private UserTypeService userTypeService;

    /**
     * 分页获得用户类型列表或者查询并分页获得用户类型列表
     *
     * @param current  当前页
     * @param size     获取几条
     * @param userType 需要查询的用户类型参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得用户类型列表或查询并分页获得用户类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "userType", value = "需要查询的用户类型参数", dataType = "UserType", dataTypeClass = UserType.class),
    })
    @PostMapping("/userTypes/page/get/{current}/{size}")
    public Result searchOrUserTypeList(@PathVariable int current,
                                       @PathVariable int size,
                                       @RequestBody(required = false) UserType userType) {
        //校验 TODO  如有请写 userType允许为空 需先判空
        Page<UserType> pageUserTypeList = userTypeService.searchOrGetUserTypeList(current, size, userType);
        List<UserType> records = pageUserTypeList.getRecords();
        long total = pageUserTypeList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定用户类型
     *
     * @param id 前端传进的用户类型id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定用户类型")
    @GetMapping("/userType/delete/{id}")
    public Result deleteUserTypeById(@PathVariable Long id) {
        int i = userTypeService.deleteUserTypeById(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id批量删除指定用户类型
     *
     * @param ids 前端传进的用户类型id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定用户类型")
    @PostMapping("/userTypes/deleteBatch")
    public Result deleteUserTypeBatchByIds(@RequestBody Long[] ids) {
        int i = userTypeService.deleteUserTypeBatchByIds(ids);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id更新指定用户类型信息
     *
     * @param userType 前端传进的用户类型实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定用户类型信息")
    @PostMapping("/userType/update")
    public Result updateUserTypeById(@RequestBody UserType userType) {
        userType.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = userTypeService.updateUserTypeById(userType, userType.getId());
        return i > 0 ? Result.ok() : Result.error();
    }


    /**
     * 添加用户类型
     *
     * @param userType 前端传进的用户类型实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加用户类型")
    @PostMapping("/userType/add")
    public Result addUserType(@RequestBody UserType userType) {
        //校验 TODO 如有请写
        int i = userTypeService.addUserType(userType);
        return i > 0 ? Result.ok() : Result.error();
    }

}
