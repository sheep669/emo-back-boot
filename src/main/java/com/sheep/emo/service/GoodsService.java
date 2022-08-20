package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.Goods;
import org.springframework.stereotype.Service;

/**
 * 操作商品的服务接口
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@Service
public interface GoodsService {


    /**
     * 分页获得商品列表或者查询并分页获得商品列表
     *
     * @param current 当前第几页
     * @param size    每页条数
     * @param goods   查询条件
     * @return Page<Goods>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<Goods> searchOrGetGoodsList(int current, int size, Goods goods);

    /**
     * 根据id删除指定商品
     *
     * @param id 商品id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteGoodsById(Long id);

    /**
     * 批量删除商品
     *
     * @param ids 商品id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteGoodsBatchByIds(Long[] ids);

    /**
     * 根据id更新指定商品信息
     *
     * @param goods 商品实体
     * @param id    商品id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateGoodsById(Goods goods, Long id);

    /**
     * 添加商品
     *
     * @param goods 商品实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addGoods(Goods goods);

}

