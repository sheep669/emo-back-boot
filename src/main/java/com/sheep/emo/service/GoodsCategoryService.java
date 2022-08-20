package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GoodsCategory;
import org.springframework.stereotype.Service;

/**
 * 操作产品分类的服务接口
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@Service
public interface GoodsCategoryService {


    /**
     * 分页获得产品分类列表或者查询并分页获得产品分类列表
     *
     * @param current       当前第几页
     * @param size          每页条数
     * @param goodsCategory 查询条件
     * @return Page<GoodsCategory>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<GoodsCategory> searchOrGetGoodsCategoryList(int current, int size, GoodsCategory goodsCategory);

    /**
     * 根据id删除指定产品分类
     *
     * @param id 产品分类id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteGoodsCategoryById(Long id);

    /**
     * 批量删除产品分类
     *
     * @param ids 产品分类id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteGoodsCategoryBatchByIds(Long[] ids);

    /**
     * 根据id更新指定产品分类信息
     *
     * @param goodsCategory 产品分类实体
     * @param id            产品分类id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateGoodsCategoryById(GoodsCategory goodsCategory, Long id);

    /**
     * 添加产品分类
     *
     * @param goodsCategory 产品分类实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addGoodsCategory(GoodsCategory goodsCategory);

}

