package com.sheep.emo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.Member;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.MemberService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作会员的控制器
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@RestController
@Api(tags = "操作会员")
@ApiResponses({
        @ApiResponse(code = 200, message = "请求成功"),
        @ApiResponse(code = 201, message = "请求已被实现"),
        @ApiResponse(code = 400, message = "请求失败"),
        @ApiResponse(code = 401, message = "没有权限"),
        @ApiResponse(code = 403, message = "禁止访问"),
        @ApiResponse(code = 404, message = "未找到")
})
public class MemberController {


    @Autowired
    private MemberService memberService;

    /**
     * 分页获得会员列表或者查询并分页获得会员列表
     *
     * @param current 当前页
     * @param size    获取几条
     * @param member  需要查询的会员参数封装
     * @return Result
     * @author sheep669
     * @created at 2022/8/7 13:48
     */
    @ApiOperation(value = "分页获得会员列表或查询并分页获得会员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "size", value = "获取几条", dataType = "int", dataTypeClass = int.class),
            @ApiImplicitParam(name = "member", value = "需要查询的会员参数", dataType = "Member", dataTypeClass = Member.class),
    })
    @PostMapping("/members/page/get/{current}/{size}")
    public Result searchOrMemberList(@PathVariable int current,
                                     @PathVariable int size,
                                     @RequestBody(required = false) Member member) {
        //校验 TODO  如有请写 member允许为空 需先判空
        Page<Member> pageMemberList = memberService.searchOrGetMemberList(current, size, member);
        List<Member> records = pageMemberList.getRecords();
        long total = pageMemberList.getTotal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("records", records);
        map.put("total", total);
        return Result.ok().data(map);
    }

    /**
     * 根据id删除指定会员
     *
     * @param id 前端传进的会员id
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id删除指定会员")
    @GetMapping("/member/delete/{id}")
    public Result deleteMemberById(@PathVariable Long id) {
        int i = memberService.deleteMemberById(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id批量删除指定会员
     *
     * @param ids 前端传进的会员id数组
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:32
     */
    @ApiOperation(value = "根据id批量删除指定会员")
    @PostMapping("/members/deleteBatch")
    public Result deleteMemberBatchByIds(@RequestBody Long[] ids) {
        int i = memberService.deleteMemberBatchByIds(ids);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 根据id更新指定会员信息
     *
     * @param member 前端传进的会员实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "更新指定会员信息")
    @PostMapping("/member/update")
    public Result updateMemberById(@RequestBody Member member) {
        member.setUpdateTime(new Date(System.currentTimeMillis()));
        //校验 TODO 如有请写
        int i = memberService.updateMemberById(member, member.getId());
        return i > 0 ? Result.ok() : Result.error();
    }


    /**
     * 添加会员
     *
     * @param member 前端传进的会员实体
     * @return Result
     * @author sheep669
     * @created at 2022/8/1 10:33
     */
    @ApiOperation(value = "添加会员")
    @PostMapping("/member/add")
    public Result addMember(@RequestBody Member member) {
        //校验 TODO 如有请写
        int i = memberService.addMember(member);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 加入黑名单
     *
     * @param id 会员id
     * @return Result
     * @author sheep669
     * @created at 2022/8/20 10:05
     */
    @ApiOperation(value = "加入黑名单")
    @PostMapping("/member/addBlacklist/{id}")
    public Result addBlacklist(@PathVariable Long id) {
        int i = memberService.addBlacklist(id);
        return i > 0 ? Result.ok() : Result.error();
    }

    /**
     * 移出黑名单
     *
     * @param id 会员id
     * @return Result
     * @author sheep669
     * @created at 2022/8/20 10:05
     */
    @ApiOperation(value = "移出黑名单")
    @PostMapping("/member/removeBlacklist/{id}")
    public Result removeBlacklist(@PathVariable Long id) {
        int i = memberService.removeBlacklist(id);
        return i > 0 ? Result.ok() : Result.error();
    }

}
