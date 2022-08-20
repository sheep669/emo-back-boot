package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.GoodsLabelMapper;
import com.sheep.emo.pojo.GoodsLabel;
import com.sheep.emo.service.GoodsLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作商品标签的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@Service
public class GoodsLabelServiceImpl implements GoodsLabelService {

    @Autowired
    private GoodsLabelMapper goodsLabelMapper;


    @Override
    public Page<GoodsLabel> searchOrGetGoodsLabelList(int current, int size, GoodsLabel goodsLabel) {
        Page<GoodsLabel> page = new Page<>(current, size);
        QueryWrapper<GoodsLabel> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(goodsLabel)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(goodsLabel.getId()) && goodsLabel.getId() != 0) {
                queryWrapper.eq("id", goodsLabel.getId());
            }
            if (StrUtil.isNotBlank(goodsLabel.getLabelName())) {
                queryWrapper.eq("label_name", goodsLabel.getLabelName());
            }
            if (ObjectUtil.isNotNull(goodsLabel.getCommodityCode()) && goodsLabel.getCommodityCode() != 0) {
                queryWrapper.eq("commodity_code", goodsLabel.getCommodityCode());
            }
            return goodsLabelMapper.selectPage(page, queryWrapper);
        }
        return goodsLabelMapper.selectPage(page, null);
    }


    @Override
    public int deleteGoodsLabelById(Long id) {
        return goodsLabelMapper.deleteById(id);
    }

    @Override
    public int deleteGoodsLabelBatchByIds(Long[] ids) {
        return goodsLabelMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateGoodsLabelById(GoodsLabel goodsLabel, Long id) {
        LambdaQueryWrapper<GoodsLabel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsLabel::getId, id);
        return goodsLabelMapper.update(goodsLabel, wrapper);
    }

    @Override
    public int addGoodsLabel(GoodsLabel goodsLabel) {
        return goodsLabelMapper.insert(goodsLabel);
    }

}

