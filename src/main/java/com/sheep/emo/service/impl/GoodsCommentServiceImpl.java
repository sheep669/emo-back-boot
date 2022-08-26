package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.GoodsCommentMapper;
import com.sheep.emo.pojo.GoodsComment;
import com.sheep.emo.service.GoodsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作商品评论的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-20
 */

@Service
public class GoodsCommentServiceImpl implements GoodsCommentService {

    @Autowired
    private GoodsCommentMapper goodsCommentMapper;


    @Override
    public Page<GoodsComment> searchOrGetGoodsCommentList(int current, int size, GoodsComment goodsComment) {
        Page<GoodsComment> page = new Page<>(current, size);
        QueryWrapper<GoodsComment> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(goodsComment)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(goodsComment.getId()) && goodsComment.getId() != 0) {
                queryWrapper.eq("id", goodsComment.getId());
            }
            if (StrUtil.isNotBlank(goodsComment.getBuyerInformation())) {
                queryWrapper.eq("buyer_information", goodsComment.getBuyerInformation());
            }
            if (StrUtil.isNotBlank(goodsComment.getGoodsName())) {
                queryWrapper.eq("goods_name", goodsComment.getGoodsName());
            }
            if (StrUtil.isNotBlank(goodsComment.getGoodsSpecification())) {
                queryWrapper.eq("goods_specification", goodsComment.getGoodsSpecification());
            }
            if (StrUtil.isNotBlank(goodsComment.getCommentContent())) {
                queryWrapper.eq("comment_content", goodsComment.getCommentContent());
            }
            if (StrUtil.isNotBlank(goodsComment.getCommentImage())) {
                queryWrapper.eq("comment_image", goodsComment.getCommentImage());
            }
            if (StrUtil.isNotBlank(goodsComment.getAuditStatus())) {
                queryWrapper.eq("audit_status", goodsComment.getAuditStatus());
            }
            return goodsCommentMapper.selectPage(page, queryWrapper);
        }
        return goodsCommentMapper.selectPage(page, null);
    }


    @Override
    public int deleteGoodsCommentById(Long id) {
        return goodsCommentMapper.deleteById(id);
    }

    @Override
    public int deleteGoodsCommentBatchByIds(Long[] ids) {
        return goodsCommentMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateGoodsCommentById(GoodsComment goodsComment, Long id) {
        LambdaQueryWrapper<GoodsComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsComment::getId, id);
        return goodsCommentMapper.update(goodsComment, wrapper);
    }

    @Override
    public int addGoodsComment(GoodsComment goodsComment) {
        return goodsCommentMapper.insert(goodsComment);
    }

    @Override
    public int confirmAudit(Long id) {
        UpdateWrapper<GoodsComment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "2");
        return goodsCommentMapper.update(null, updateWrapper);
    }

    @Override
    public int approveAudit(Long id) {
        UpdateWrapper<GoodsComment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "3");
        return goodsCommentMapper.update(null, updateWrapper);
    }

    @Override
    public int rejectAudit(Long id) {
        UpdateWrapper<GoodsComment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "4");
        return goodsCommentMapper.update(null, updateWrapper);
    }

    @Override
    public int reAudit(Long id) {
        UpdateWrapper<GoodsComment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "1");
        return goodsCommentMapper.update(null, updateWrapper);
    }
}

