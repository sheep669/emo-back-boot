package com.sheep.emo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sheep.emo.mapper.*;
import com.sheep.emo.pojo.*;
import com.sheep.emo.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/9/8 8:26
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GroupBuyingOrganizerMapper groupBuyingOrganizerMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private AfterSaleMapper afterSaleMapper;

    @Override
    public Map<String, Object> getPieChartData() {
        QueryWrapper<Member> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("member_type", "1");
        Long memberVipNum = memberMapper.selectCount(queryWrapper1);
        QueryWrapper<Member> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("member_type", "2");
        Long memberSvipNum = memberMapper.selectCount(queryWrapper2);
        Long userNum = userMapper.selectCount(null);
        QueryWrapper<User> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("role", "shopOwners");
        Long shopOwnerNum = userMapper.selectCount(queryWrapper3);
        Long groupBuyingOrganizerNum = groupBuyingOrganizerMapper.selectCount(null);
        Map<String, Object> map = new HashMap<>(12);
        map.put("memberVipNum", memberVipNum);
        map.put("memberSvipNum", memberSvipNum);
        map.put("userNum", userNum);
        map.put("shopOwnerNum", shopOwnerNum);
        map.put("groupBuyingOrganizerNum", groupBuyingOrganizerNum);
        return map;
    }

    @Override
    public Map<String, Object> getOrderData() {
        QueryWrapper<Orders> queryWrapper4 = new QueryWrapper<>();
        queryWrapper4.eq("order_status", "2");
        Long unPreparedOrderNum = ordersMapper.selectCount(queryWrapper4);
        Long todayOrderNum = ordersMapper.countTodayOrderNum();
        Map<String, Object> map = new HashMap<>(12);
        map.put("unPreparedOrderNum", unPreparedOrderNum);
        map.put("todayOrderNum", todayOrderNum);
        return map;
    }

    @Override
    public Map<String, Object> getInventoryData() {
        QueryWrapper<Goods> queryWrapper5 = new QueryWrapper<>();
        queryWrapper5.lt("total_stocks", 5);
        Long inventoryWarningNum = goodsMapper.selectCount(queryWrapper5);
        Long totalStocks = goodsMapper.getTotalStocks();
        Map<String, Object> map = new HashMap<>(12);
        map.put("inventoryWarningNum", inventoryWarningNum);
        map.put("totalStocks", totalStocks);
        return map;
    }

    @Override
    public Map<String, Object> getGroupOrganizerData() {
        Long totalGroupBuyingOrganizerNum = groupBuyingOrganizerMapper.selectCount(null);
        QueryWrapper<GroupBuyingOrganizer> groupBuyingOrganizerQueryWrapper = new QueryWrapper<>();
        groupBuyingOrganizerQueryWrapper.eq("audit_status", "1");
        Long waitAuditNum = groupBuyingOrganizerMapper.selectCount(groupBuyingOrganizerQueryWrapper);
        Map<String, Object> map = new HashMap<>(12);
        map.put("totalGroupBuyingOrganizerNum", totalGroupBuyingOrganizerNum);
        map.put("waitAuditGroupBuyingOrganizerNum", waitAuditNum);
        return map;
    }

    @Override
    public Map<String, Object> getAfterSaleData() {
        Long totalAfterSaleNum = afterSaleMapper.selectCount(null);
        QueryWrapper<AfterSale> afterSaleQueryWrapper = new QueryWrapper<>();
        afterSaleQueryWrapper.eq("audit_status", "1");
        Long waitAuditNum = afterSaleMapper.selectCount(afterSaleQueryWrapper);
        Map<String, Object> map = new HashMap<>(12);
        map.put("totalAfterSaleNum", totalAfterSaleNum);
        map.put("waitAuditAfterSaleNum", waitAuditNum);
        return map;
    }
}

