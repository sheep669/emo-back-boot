package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.Goods;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.GoodsService;
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
 * 操作商品的控制器
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@RestController
@Api(tags = "操作商品")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemOperateLogService systemOperateLogService;


    /**
     * 分页获得商品列表或者查询并分页获得商品列表
     *
     * @param current 当前页
     * @param size    获取几条
     * @param goods   需要查询的商品参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得商品列表或查询并分页获得商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "goods", value = "需要查询的商品参数", dataType = "Goods", dataTypeClass = Goods.class),
    })
    @PostMapping("/goods/page/get/{current}/{size}")
    public Result searchOrGetGoodsList(@PathVariable int current,
                                       @PathVariable int size,
                                       @RequestBody(required = false) Goods goods) {
        //校验 TODO  如有请写 goods允许为空 需先判空
        Page<Goods> pageGoodsList = goodsService.searchOrGetGoodsList(current, size, goods);
        List<Goods> records = pageGoodsList.getRecords();
        long total = pageGoodsList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定商品
     *
     * @param id 前端传进的商品id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定商品")
    @GetMapping("/goods/delete/{id}")
    public Result deleteGoodsById(@PathVariable Long id) {
        int i = goodsService.deleteGoodsById(id);
        if (i > 0) {
            addLog("删除", "商品", "1");
            return Result.ok();
        } else {
            addLog("删除", "商品", "0");
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
     * 根据id批量删除指定商品
     *
     * @param ids 前端传进的商品id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定商品")
    @PostMapping("/goods/deleteBatch")
    public Result deleteGoodsBatchByIds(@RequestBody Long[] ids) {
        int i = goodsService.deleteGoodsBatchByIds(ids);
        if (i > 0) {
            addLog("批量删除", "商品", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "商品", "0");
            return Result.error();
        }
    }

    /**
     * 根据id更新指定商品信息
     *
     * @param goods 前端传进的商品实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定商品信息")
    @PostMapping("/goods/update")
    public Result updateGoodsById(@RequestBody Goods goods) {
        goods.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = goodsService.updateGoodsById(goods, goods.getId());
        if (i > 0) {
            addLog("更新", "商品", "1");
            return Result.ok();
        } else {
            addLog("更新", "商品", "0");
            return Result.error();
        }
    }


    /**
     * 添加商品
     *
     * @param goods 前端传进的商品实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加商品")
    @PostMapping("/goods/add")
    public Result addGoods(@RequestBody Goods goods) {
        //校验 TODO 如有请写
        int i = goodsService.addGoods(goods);
        if (i > 0) {
            addLog("添加", "商品", "1");
            return Result.ok();
        } else {
            addLog("添加", "商品", "0");
            return Result.error();
        }
    }

}
