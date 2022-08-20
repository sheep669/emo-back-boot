package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GoodsComment;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.GoodsCommentService;
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
    public Result searchOrGoodsCommentList(@PathVariable int current,
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
        return i > 0 ? Result.ok() : Result.error();
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
    @PostMapping("/goodsComment/deleteBatch")
    public Result deleteGoodsCommentBatchByIds(@RequestBody Long[] ids) {
        int i = goodsCommentService.deleteGoodsCommentBatchByIds(ids);
        return i > 0 ? Result.ok() : Result.error();
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
        return i > 0 ? Result.ok() : Result.error();
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
        return i > 0 ? Result.ok() : Result.error();
    }

}