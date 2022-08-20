package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.GoodsCategoryMapper;
import com.sheep.emo.pojo.GoodsCategory;
import com.sheep.emo.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作产品分类的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;


    @Override
    public Page<GoodsCategory> searchOrGetGoodsCategoryList(int current, int size, GoodsCategory goodsCategory) {
        Page<GoodsCategory> page = new Page<>(current, size);
        QueryWrapper<GoodsCategory> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(goodsCategory)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(goodsCategory.getId()) && goodsCategory.getId() != 0) {
                queryWrapper.eq("id", goodsCategory.getId());
            }
            if (StrUtil.isNotBlank(goodsCategory.getCategoryName())) {
                queryWrapper.eq("category_name", goodsCategory.getCategoryName());
            }
            if (ObjectUtil.isNotNull(goodsCategory.getGoodsNumber()) && goodsCategory.getGoodsNumber() != 0) {
                queryWrapper.eq("goods_number", goodsCategory.getGoodsNumber());
            }
            if (ObjectUtil.isNotNull(goodsCategory.getSort()) && goodsCategory.getSort() != 0) {
                queryWrapper.eq("sort", goodsCategory.getSort());
            }
            if (ObjectUtil.isNotNull(goodsCategory.getAddTime())) {
                queryWrapper.eq("add_time", goodsCategory.getAddTime());
            }
            return goodsCategoryMapper.selectPage(page, queryWrapper);
        }
        return goodsCategoryMapper.selectPage(page, null);
    }


    @Override
    public int deleteGoodsCategoryById(Long id) {
        return goodsCategoryMapper.deleteById(id);
    }

    @Override
    public int deleteGoodsCategoryBatchByIds(Long[] ids) {
        return goodsCategoryMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateGoodsCategoryById(GoodsCategory goodsCategory, Long id) {
        LambdaQueryWrapper<GoodsCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsCategory::getId, id);
        return goodsCategoryMapper.update(goodsCategory, wrapper);
    }

    @Override
    public int addGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryMapper.insert(goodsCategory);
    }

}

