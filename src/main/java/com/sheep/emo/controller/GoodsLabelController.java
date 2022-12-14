package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.sheep.emo.pojo.GoodsLabel;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.GoodsLabelService;
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
 * 操作商品标签的控制器
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@RestController
@Api(tags = "操作商品标签")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class GoodsLabelController {


    @Autowired
    private GoodsLabelService goodsLabelService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemOperateLogService systemOperateLogService;

    /**
     * 分页获得商品标签列表或者查询并分页获得商品标签列表
     *
     * @param current    当前页
     * @param size       获取几条
     * @param goodsLabel 需要查询的商品标签参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得商品标签列表或查询并分页获得商品标签列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "goodsLabel", value = "需要查询的商品标签参数", dataType = "GoodsLabel", dataTypeClass = GoodsLabel.class),
    })
    @PostMapping("/goodsLabels/page/get/{current}/{size}")
    public Result searchOrGetGoodsLabelList(@PathVariable int current,
                                         @PathVariable int size,
                                         @RequestBody(required = false) GoodsLabel goodsLabel) {
        //校验 TODO  如有请写 goodsLabel允许为空 需先判空
        Page<GoodsLabel> pageGoodsLabelList = goodsLabelService.searchOrGetGoodsLabelList(current, size, goodsLabel);
        List<GoodsLabel> records = pageGoodsLabelList.getRecords();
        long total = pageGoodsLabelList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定商品标签
     *
     * @param id 前端传进的商品标签id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定商品标签")
    @GetMapping("/goodsLabel/delete/{id}")
    public Result deleteGoodsLabelById(@PathVariable Long id) {
        int i = goodsLabelService.deleteGoodsLabelById(id);
        if (i > 0) {
            addLog("删除", "商品标签", "1");
            return Result.ok();
        } else {
            addLog("删除", "商品标签", "0");
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
     * 根据id批量删除指定商品标签
     *
     * @param ids 前端传进的商品标签id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定商品标签")
    @PostMapping("/goodsLabels/deleteBatch")
    public Result deleteGoodsLabelBatchByIds(@RequestBody Long[] ids) {
        int i = goodsLabelService.deleteGoodsLabelBatchByIds(ids);
        if (i > 0) {
            addLog("批量删除", "商品标签", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "商品标签", "0");
            return Result.error();
        }
    }

    /**
     * 根据id更新指定商品标签信息
     *
     * @param goodsLabel 前端传进的商品标签实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定商品标签信息")
    @PostMapping("/goodsLabel/update")
    public Result updateGoodsLabelById(@RequestBody GoodsLabel goodsLabel) {
        goodsLabel.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = goodsLabelService.updateGoodsLabelById(goodsLabel, goodsLabel.getId());
        if (i > 0) {
            addLog("更新", "商品标签", "1");
            return Result.ok();
        } else {
            addLog("更新", "商品标签", "0");
            return Result.error();
        }
    }


    /**
     * 添加商品标签
     *
     * @param goodsLabel 前端传进的商品标签实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加商品标签")
    @ApiOperationSupport(ignoreParameters = "id")
    @PostMapping("/goodsLabel/add")
    public Result addGoodsLabel(@RequestBody GoodsLabel goodsLabel) {
        //校验 TODO 如有请写
        int i = goodsLabelService.addGoodsLabel(goodsLabel);
        if (i > 0) {
            addLog("添加", "商品标签", "1");
            return Result.ok();
        } else {
            addLog("添加", "商品标签", "0");
            return Result.error();
        }
    }

}
