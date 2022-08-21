package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GroupBuyingOrganizerClear;
import org.springframework.stereotype.Service;

/**
 * 操作团长结算的服务接口
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@Service
public interface GroupBuyingOrganizerClearService {


    /**
     * 分页获得团长结算列表或者查询并分页获得团长结算列表
     *
     * @param current                   当前第几页
     * @param size                      每页条数
     * @param groupBuyingOrganizerClear 查询条件
     * @return Page<GroupBuyingOrganizerClear>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<GroupBuyingOrganizerClear> searchOrGetGroupBuyingOrganizerClearList(int current, int size, GroupBuyingOrganizerClear groupBuyingOrganizerClear);

    /**
     * 根据id删除指定团长结算
     *
     * @param id 团长结算id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteGroupBuyingOrganizerClearById(Long id);

    /**
     * 批量删除团长结算
     *
     * @param ids 团长结算id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteGroupBuyingOrganizerClearBatchByIds(Long[] ids);

    /**
     * 根据id更新指定团长结算信息
     *
     * @param groupBuyingOrganizerClear 团长结算实体
     * @param id                        团长结算id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateGroupBuyingOrganizerClearById(GroupBuyingOrganizerClear groupBuyingOrganizerClear, Long id);

    /**
     * 添加团长结算
     *
     * @param groupBuyingOrganizerClear 团长结算实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addGroupBuyingOrganizerClear(GroupBuyingOrganizerClear groupBuyingOrganizerClear);

}

