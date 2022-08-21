package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.GroupBuyingOrganizerWithdrawalInformation;
import org.springframework.stereotype.Service;

/**
 * 操作团长提现信息的服务接口
 *
 * @author sheep669
 * @created at 2022-08-21
 */

@Service
public interface GroupBuyingOrganizerWithdrawalInformationService {


    /**
     * 分页获得团长提现信息列表或者查询并分页获得团长提现信息列表
     *
     * @param current                                   当前第几页
     * @param size                                      每页条数
     * @param groupBuyingOrganizerWithdrawalInformation 查询条件
     * @return Page<GroupBuyingOrganizerWithdrawalInformation>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<GroupBuyingOrganizerWithdrawalInformation> searchOrGetGroupBuyingOrganizerWithdrawalInformationList(int current, int size, GroupBuyingOrganizerWithdrawalInformation groupBuyingOrganizerWithdrawalInformation);

    /**
     * 根据id删除指定团长提现信息
     *
     * @param id 团长提现信息id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteGroupBuyingOrganizerWithdrawalInformationById(Long id);

    /**
     * 批量删除团长提现信息
     *
     * @param ids 团长提现信息id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteGroupBuyingOrganizerWithdrawalInformationBatchByIds(Long[] ids);

    /**
     * 根据id更新指定团长提现信息信息
     *
     * @param groupBuyingOrganizerWithdrawalInformation 团长提现信息实体
     * @param id                                        团长提现信息id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateGroupBuyingOrganizerWithdrawalInformationById(GroupBuyingOrganizerWithdrawalInformation groupBuyingOrganizerWithdrawalInformation, Long id);

    /**
     * 添加团长提现信息
     *
     * @param groupBuyingOrganizerWithdrawalInformation 团长提现信息实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addGroupBuyingOrganizerWithdrawalInformation(GroupBuyingOrganizerWithdrawalInformation groupBuyingOrganizerWithdrawalInformation);

    /**
     * 确认团长提现信息审核
     *
     * @param id 团长提现信息id
     * @return int
     * @author sheep669
     * @created at 2022/8/15 17:47
     */
    int confirmAudit(Long id);

    /**
     * 通过审核
     *
     * @param id 团长提现信息id
     * @return int
     * @author sheep669
     * @created at 2022/8/16 19:54
     */
    int approveAudit(Long id);


    /**
     * 拒绝审核
     *
     * @param id 团长提现信息id
     * @return int
     * @author sheep669
     * @created at 2022/8/16 19:55
     */
    int rejectAudit(Long id);

    /**
     * 重新审核
     *
     * @param id 团长提现信息id
     * @return int
     * @author sheep669
     * @created at 2022/8/20 10:06
     */
    int reAudit(Long id);
}

