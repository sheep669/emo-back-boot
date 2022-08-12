package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GroupBuyingOrganizerAudit;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author sheep669
 * @created at 2022-08-12
 */

@Service
public interface GroupBuyingOrganizerAuditService {


    /**
     * 分页获得团长审核列表或者查询并分页获得团长审核列表
     *
     * @param current                   当前第几页
     * @param size                      每页条数
     * @param groupBuyingOrganizerAudit 查询条件
     * @return Page<GroupBuyingOrganizerAudit>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<GroupBuyingOrganizerAudit> searchOrGetGroupBuyingOrganizerAuditList(int current, int size, GroupBuyingOrganizerAudit groupBuyingOrganizerAudit);

    /**
     * 根据id删除指定团长审核
     *
     * @param id 团长审核id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteGroupBuyingOrganizerAuditById(Long id);

    /**
     * 批量删除团长审核
     *
     * @param ids 团长审核id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteGroupBuyingOrganizerAuditBatchByIds(Long[] ids);

    /**
     * 根据id更新指定团长审核信息
     *
     * @param groupBuyingOrganizerAudit 团长审核实体
     * @param id                        团长审核id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateGroupBuyingOrganizerAuditById(GroupBuyingOrganizerAudit groupBuyingOrganizerAudit, Long id);

    /**
     * 添加团长审核
     *
     * @param groupBuyingOrganizerAudit 团长审核实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addGroupBuyingOrganizerAudit(GroupBuyingOrganizerAudit groupBuyingOrganizerAudit);

}

