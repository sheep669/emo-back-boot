package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.MemberLabel;
import org.springframework.stereotype.Service;

/**
 * 操作会员标签的服务接口
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public interface MemberLabelService {


    /**
     * 分页获得会员标签列表或者查询并分页获得会员标签列表
     *
     * @param current     当前第几页
     * @param size        每页条数
     * @param memberLabel 查询条件
     * @return Page<MemberLabel>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<MemberLabel> searchOrGetMemberLabelList(int current, int size, MemberLabel memberLabel);

    /**
     * 根据id删除指定会员标签
     *
     * @param id 会员标签id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteMemberLabelById(Long id);

    /**
     * 批量删除会员标签
     *
     * @param ids 会员标签id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteMemberLabelBatchByIds(Long[] ids);

    /**
     * 根据id更新指定会员标签信息
     *
     * @param memberLabel 会员标签实体
     * @param id          会员标签id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateMemberLabelById(MemberLabel memberLabel, Long id);

    /**
     * 添加会员标签
     *
     * @param memberLabel 会员标签实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addMemberLabel(MemberLabel memberLabel);

}

