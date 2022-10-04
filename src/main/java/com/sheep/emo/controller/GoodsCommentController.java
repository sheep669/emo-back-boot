package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GoodsComment;
import com.sheep.emo.pojo.SystemOperateLog;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.GoodsCommentService;
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
 * 操作商品评论的控制器
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@RestController
@Api(tags = "操作商品评论")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class GoodsCommentController {


    @Autowired
    private GoodsCommentService goodsCommentService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemOperateLogService systemOperateLogService;

    /**
     * 分页获得商品评论列表或者查询并分页获得商品评论列表
     *
     * @param current      当前页
     * @param size         获取几条
     * @param goodsComment 需要查询的商品评论参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得商品评论列表或查询并分页获得商品评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "goodsComment", value = "需要查询的商品评论参数", dataType = "GoodsComment", dataTypeClass = GoodsComment.class),
    })
    @PostMapping("/goodsComments/page/get/{current}/{size}")
    public Result searchOrGetGoodsCommentList(@PathVariable int current,
                                           @PathVariable int size,
                                           @RequestBody(required = false) GoodsComment goodsComment) {
        //校验 TODO  如有请写 goodsComment允许为空 需先判空
        Page<GoodsComment> pageGoodsCommentList = goodsCommentService.searchOrGetGoodsCommentList(current, size, goodsComment);
        List<GoodsComment> records = pageGoodsCommentList.getRecords();
        long total = pageGoodsCommentList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定商品评论
     *
     * @param id 前端传进的商品评论id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定商品评论")
    @GetMapping("/goodsComment/delete/{id}")
    public Result deleteGoodsCommentById(@PathVariable Long id) {
        int i = goodsCommentService.deleteGoodsCommentById(id);
        if (i > 0) {
            addLog("删除", "商品评论", "1");
            return Result.ok();
        } else {
            addLog("删除", "商品评论", "0");
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
     * 根据id批量删除指定商品评论
     *
     * @param ids 前端传进的商品评论id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定商品评论")
    @PostMapping("/goodsComments/deleteBatch")
    public Result deleteGoodsCommentBatchByIds(@RequestBody Long[] ids) {
        int i = goodsCommentService.deleteGoodsCommentBatchByIds(ids);
        if (i > 0) {
            addLog("批量删除", "商品评论", "1");
            return Result.ok();
        } else {
            addLog("批量删除", "商品评论", "0");
            return Result.error();
        }
    }

    /**
     * 根据id更新指定商品评论信息
     *
     * @param goodsComment 前端传进的商品评论实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定商品评论信息")
    @PostMapping("/goodsComment/update")
    public Result updateGoodsCommentById(@RequestBody GoodsComment goodsComment) {
        goodsComment.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = goodsCommentService.updateGoodsCommentById(goodsComment, goodsComment.getId());
        if (i > 0) {
            addLog("更新", "商品评论", "1");
            return Result.ok();
        } else {
            addLog("更新", "商品评论", "0");
            return Result.error();
        }
    }


    /**
     * 添加商品评论
     *
     * @param goodsComment 前端传进的商品评论实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加商品评论")
    @PostMapping("/goodsComment/add")
    public Result addGoodsComment(@RequestBody GoodsComment goodsComment) {
        //校验 TODO 如有请写
        int i = goodsCommentService.addGoodsComment(goodsComment);
        if (i > 0) {
            addLog("添加", "商品评论", "1");
            return Result.ok();
        } else {
            addLog("添加", "商品评论", "0");
            return Result.error();
        }
    }

    /**
     * 确认商品评论信息审核
     *
     * @param id 商品评论id
     * @return Result
     * @author sheep669
     * @created at 2022/8/15 17:28
     */
    @ApiOperation(value = "确认商品评论审核")
    @PostMapping("/goodsComment/audit/confirm/{id}")
    public Result confirmAudit(@PathVariable Long id) {
        int i = goodsCommentService.confirmAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 通过审核
     *
     * @param id 商品评论id
     * @return Result
     * @author sheep669
     * @created at 2022/8/15 17:28
     */
    @ApiOperation(value = "通过商品评论审核")
    @PostMapping("/goodsComment/audit/approve/{id}")
    public Result approveAudit(@PathVariable Long id) {
        int i = goodsCommentService.approveAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 拒绝审核
     *
     * @param id 商品评论id
     * @return Result
     * @author sheep669
     * @created at 2022/8/15 17:28
     */
    @ApiOperation(value = "拒绝商品评论审核")
    @PostMapping("/goodsComment/audit/reject/{id}")
    public Result rejectAudit(@PathVariable Long id) {
        int i = goodsCommentService.rejectAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 重新审核
     *
     * @param id 商品评论id
     * @return Result
     * @author sheep669
     * @created at 2022/8/20 10:05
     */
    @ApiOperation(value = "重新审核")
    @PostMapping("/goodsComment/audit/reAudit/{id}")
    public Result reAudit(@PathVariable Long id) {
        int i = goodsCommentService.reAudit(id);
        return i > 0 ? Result.ok() : Result.error();
    }


}
