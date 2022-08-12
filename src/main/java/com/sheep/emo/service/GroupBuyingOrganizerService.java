package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GroupBuyingOrganizer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : sheep669
 * @description : 团长的增删改查服务层接口
 * @created at 2022/7/29 11:20
 */
@Service
public interface GroupBuyingOrganizerService {
    /**
     * 获得团长列表
     *
     * @return List<GroupBuyingOrganizer>
     * @author sheep669
     * @created at 2022/7/29 11:51
     */
    List<GroupBuyingOrganizer> getGroupBuyingOrganizerList();

    // /**
    //  * 分页获得团长列表
    //  *
    //  * @param current 当前第几页
    //  * @param size    每页条数
    //  * @return Page<GroupBuyingOrganizer>
    //  * @author sheep669
    //  * @created at 2022/8/3 20:29
    //  */
    // Page<GroupBuyingOrganizer> getPageGroupBuyingOrganizerList(int current, int size);

    /**
     * 分页获得团长列表或者查询并分页获得团长列表
     *
     * @param current              当前第几页
     * @param size                 每页条数
     * @param groupBuyingOrganizer 查询条件
     * @return Page<GroupBuyingOrganizer>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<GroupBuyingOrganizer> searchOrGetGroupBuyingOrganizerList(int current, int size, GroupBuyingOrganizer groupBuyingOrganizer);


    /**
     * 根据id删除指定团长
     *
     * @param id 团长id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteGroupBuyingOrganizerById(Long id);

    /**
     * 批量删除团长
     *
     * @param ids 团长id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteGroupBuyingOrganizerBatchByIds(Long[] ids);

    /**
     * 根据id更新指定团长信息
     *
     * @param groupBuyingOrganizer 团长实体
     * @param id                   团长id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateGroupBuyingOrganizerById(GroupBuyingOrganizer groupBuyingOrganizer, Long id);

    /**
     * 添加团长
     *
     * @param groupBuyingOrganizer 团长实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addGroupBuyingOrganizer(GroupBuyingOrganizer groupBuyingOrganizer);
}

