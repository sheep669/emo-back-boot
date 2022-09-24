package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.GoodsMapper;
import com.sheep.emo.pojo.Goods;
import com.sheep.emo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 操作商品的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public Page<Goods> searchOrGetGoodsList(int current, int size, Goods goods) {
        Page<Goods> page = new Page<>(current, size);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(goods)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(goods.getId()) && goods.getId() != 0) {
                queryWrapper.eq("id", goods.getId());
            }
            if (StrUtil.isNotBlank(goods.getGoodsName())) {
                queryWrapper.eq("goods_name", goods.getGoodsName());
            }
            if (StrUtil.isNotBlank(goods.getGoodsCategory())) {
                queryWrapper.eq("goods_category", goods.getGoodsCategory());
            }
            if (ObjectUtil.isNotNull(goods.getGoodsPrice()) && !BigDecimal.ZERO.equals(goods.getGoodsPrice())) {
                queryWrapper.eq("goods_price", goods.getGoodsPrice());
            }
            if (ObjectUtil.isNotNull(goods.getTotalStocks()) && goods.getTotalStocks() != 0) {
                queryWrapper.eq("total_stocks", goods.getTotalStocks());
            }
            if (ObjectUtil.isNotNull(goods.getShowSales()) && goods.getShowSales() != 0) {
                queryWrapper.eq("show_sales", goods.getShowSales());
            }
            if (ObjectUtil.isNotNull(goods.getRealSales()) && goods.getRealSales() != 0) {
                queryWrapper.eq("real_sales", goods.getRealSales());
            }
            if (ObjectUtil.isNotNull(goods.getSerialNumber()) && goods.getSerialNumber() != 0) {
                queryWrapper.eq("serial_number", goods.getSerialNumber());
            }
            if (StrUtil.isNotBlank(goods.getGoodsStatus())) {
                queryWrapper.eq("goods_status", goods.getGoodsStatus());
            }
            if (ObjectUtil.isNotNull(goods.getMakeTime())) {
                queryWrapper.eq("make_time", goods.getMakeTime());
            }
            return goodsMapper.selectPage(page, queryWrapper);
        }
        return goodsMapper.selectPage(page, null);
    }


    @Override
    public int deleteGoodsById(Long id) {
        return goodsMapper.deleteById(id);
    }

    @Override
    public int deleteGoodsBatchByIds(Long[] ids) {
        return goodsMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateGoodsById(Goods goods, Long id) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getId, id);
        return goodsMapper.update(goods, wrapper);
    }

    @Override
    public int addGoods(Goods goods) {
        return goodsMapper.insert(goods);
    }
}

