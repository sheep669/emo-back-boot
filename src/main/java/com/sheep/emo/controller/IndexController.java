package com.sheep.emo.controller;

import com.sheep.emo.response.Result;
import com.sheep.emo.service.GroupBuyingOrganizerService;
import com.sheep.emo.service.IndexService;
import com.sheep.emo.service.OrdersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/9/24 14:18
 */
@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;
    @Autowired
    private GroupBuyingOrganizerService groupBuyingOrganizerService;
    @Autowired
    private OrdersService ordersService;


    @ApiOperation(value = "获得饼状图数据")
    @GetMapping("/getPieChartData")
    public Result getPieChartData() {
        return Result.ok().data(indexService.getPieChartData());
    }

    @ApiOperation(value = "获得首页订单数据")
    @GetMapping("/getOrderData")
    public Result getOrderData() {
        return Result.ok().data(indexService.getOrderData());
    }

    @ApiOperation(value = "获得首页库存数据")
    @GetMapping("/getInventoryData")
    public Result getInventoryData() {
        return Result.ok().data(indexService.getInventoryData());
    }

    @ApiOperation(value = "获得首页团长数据")
    @GetMapping("/getGroupOrganizerData")
    public Result getGroupOrganizerData() {
        return Result.ok().data(indexService.getGroupOrganizerData());
    }

    @ApiOperation(value = "获得首页售后数据")
    @GetMapping("/getAfterSaleData")
    public Result getAfterSaleData() {
        return Result.ok().data(indexService.getAfterSaleData());
    }

    @ApiOperation(value = "获得首页团长佣金排行数据")
    @GetMapping("/getGroupBuyingOrganizerRank")
    public Result getGroupBuyingOrganizerRank() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("groupBuyingOrganizerRank", groupBuyingOrganizerService.getGroupBuyingOrganizerRank());
        return Result.ok().data(map);
    }

    @ApiOperation(value = "获得首页用户消费排行数据")
    @GetMapping("/getBuyerRank")
    public Result getBuyerRank() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("buyerRank", ordersService.getBuyerRank());
        return Result.ok().data(map);
    }


}

