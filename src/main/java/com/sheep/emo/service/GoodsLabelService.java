package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GoodsLabel;
import org.springframework.stereotype.Service;

/**
 * 操作商品标签的服务接口
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@Service
public interface GoodsLabelService {


    /**
     * 分页获得商品标签列表或者查询并分页获得商品标签列表
     *
     * @param current    当前第几页
     * @param size       每页条数
     * @param goodsLabel 查询条件
     * @return Page<GoodsLabel>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<GoodsLabel> searchOrGetGoodsLabelList(int current, int size, GoodsLabel goodsLabel);

    /**
     * 根据id删除指定商品标签
     *
     * @param id 商品标签id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteGoodsLabelById(Long id);

    /**
     * 批量删除商品标签
     *
     * @param ids 商品标签id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteGoodsLabelBatchByIds(Long[] ids);

    /**
     * 根据id更新指定商品标签信息
     *
     * @param goodsLabel 商品标签实体
     * @param id         商品标签id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateGoodsLabelById(GoodsLabel goodsLabel, Long id);

    /**
     * 添加商品标签
     *
     * @param goodsLabel 商品标签实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addGoodsLabel(GoodsLabel goodsLabel);

}

