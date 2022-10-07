package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.sheep.emo.pojo.GoodsCategory;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.GoodsCategoryService;
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
 * 操作产品分类的控制器
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@RestController
@Api(tags = "操作产品分类")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class GoodsCategoryController {


    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemOperateLogService systemOperateLogService;

    /**
     * 分页获得产品分类列表或者查询并分页获得产品分类列表
     *
     * @param current       当前页
     * @param size          获取几条
     * @param goodsCategory 需要查询的产品分类参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得产品分类列表或查询并分页获得产品分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "goodsCategory", value = "需要查询的产品分类参数", dataType = "GoodsCategory", dataTypeClass = GoodsCategory.class),
    })
    @PostMapping("/goodsCategories/page/get/{current}/{size}")
    public Result searchOrGetGoodsCategoryList(@PathVariable int current,
                                            @PathVariable int size,
                                            @RequestBody(required = false) GoodsCategory goodsCategory) {
        //校验 TODO  如有请写 goodsCategory允许为空 需先判空
        Page<GoodsCategory> pageGoodsCategoryList = goodsCategoryService.searchOrGetGoodsCategoryList(current, size, goodsCategory);
        List<GoodsCategory> records = pageGoodsCategoryList.getRecords();
        long total = pageGoodsCategoryList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定产品分类
     *
     * @param id 前端传进的产品分类id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定产品分类")
    @GetMapping("/goodsCategory/delete/{id}")
    public Result deleteGoodsCategoryById(@PathVariable Long id) {
        int i = goodsCategoryService.deleteGoodsCategoryById(id);
        if (i > 0) {
            addLog("删除", "产品分类", "1");
            return Result.ok();
        } else {
            addLog("删除", "产品分类", "0");
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
     * 根据id批量删除指定产品分类
     *
     * @param ids 前端传进的产品分类id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定产品分类")
    @PostMapping("/goodsCategories/deleteBatch")
    public Result deleteGoodsCategoryBatchByIds(@RequestBody Long[] ids) {
        int i = goodsCategoryService.deleteGoodsCategoryBatchByIds(ids);
        if (i > 0) {
            addLog("批量删除", "产品分类", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "产品分类", "0");
            return Result.error();
        }
    }

    /**
     * 根据id更新指定产品分类信息
     *
     * @param goodsCategory 前端传进的产品分类实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定产品分类信息")
    @PostMapping("/goodsCategory/update")
    public Result updateGoodsCategoryById(@RequestBody GoodsCategory goodsCategory) {
        goodsCategory.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = goodsCategoryService.updateGoodsCategoryById(goodsCategory, goodsCategory.getId());
        if (i > 0) {
            addLog("更新", "产品分类", "1");
            return Result.ok();
        } else {
            addLog("更新", "产品分类", "0");
            return Result.error();
        }
    }


    /**
     * 添加产品分类
     *
     * @param goodsCategory 前端传进的产品分类实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加产品分类")
    @ApiOperationSupport(ignoreParameters = "id")
    @PostMapping("/goodsCategory/add")
    public Result addGoodsCategory(@RequestBody GoodsCategory goodsCategory) {
        //校验 TODO 如有请写
        int i = goodsCategoryService.addGoodsCategory(goodsCategory);
        if (i > 0) {
            addLog("添加", "产品分类", "1");
            return Result.ok();
        } else {
            addLog("添加", "产品分类", "0");
            return Result.error();
        }
    }

}
