package com.sheep.emo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheep.emo.pojo.Goods;

/**
 * 操作商品的mapper接口
 *
 * @author sheep669
 * @created at 2022-08-20
 */

public interface GoodsMapper extends BaseMapper<Goods> {
    Long getTotalStocks();
}

