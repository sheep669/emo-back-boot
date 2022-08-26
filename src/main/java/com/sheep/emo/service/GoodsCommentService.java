package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GoodsComment;
import org.springframework.stereotype.Service;

/**
 * 操作商品评论的服务接口
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@Service
public interface GoodsCommentService {


    /**
     * 分页获得商品评论列表或者查询并分页获得商品评论列表
     *
     * @param current      当前第几页
     * @param size         每页条数
     * @param goodsComment 查询条件
     * @return Page<GoodsComment>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<GoodsComment> searchOrGetGoodsCommentList(int current, int size, GoodsComment goodsComment);

    /**
     * 根据id删除指定商品评论
     *
     * @param id 商品评论id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteGoodsCommentById(Long id);

    /**
     * 批量删除商品评论
     *
     * @param ids 商品评论id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteGoodsCommentBatchByIds(Long[] ids);

    /**
     * 根据id更新指定商品评论信息
     *
     * @param goodsComment 商品评论实体
     * @param id           商品评论id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateGoodsCommentById(GoodsComment goodsComment, Long id);

    /**
     * 添加商品评论
     *
     * @param goodsComment 商品评论实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addGoodsComment(GoodsComment goodsComment);

    /**
     * 确认商品评论信息审核
     *
     * @param id 商品评论id
     * @return int
     * @author sheep669
     * @created at 2022/8/15 17:47
     */
    int confirmAudit(Long id);

    /**
     * 通过审核
     *
     * @param id 商品评论id
     * @return int
     * @author sheep669
     * @created at 2022/8/16 19:54
     */
    int approveAudit(Long id);


    /**
     * 拒绝审核
     *
     * @param id 商品评论id
     * @return int
     * @author sheep669
     * @created at 2022/8/16 19:55
     */
    int rejectAudit(Long id);

    /**
     * 重新审核
     *
     * @param id 商品评论id
     * @return int
     * @author sheep669
     * @created at 2022/8/20 10:06
     */
    int reAudit(Long id);

}

