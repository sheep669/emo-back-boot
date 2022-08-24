package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.MemberDiscountPrivilege;
import org.springframework.stereotype.Service;

/**
 * 操作会员优惠权限的服务接口
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public interface MemberDiscountPrivilegeService {


    /**
     * 分页获得会员优惠权限列表或者查询并分页获得会员优惠权限列表
     *
     * @param current                 当前第几页
     * @param size                    每页条数
     * @param memberDiscountPrivilege 查询条件
     * @return Page<MemberDiscountPrivilege>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<MemberDiscountPrivilege> searchOrGetMemberDiscountPrivilegeList(int current, int size, MemberDiscountPrivilege memberDiscountPrivilege);

    /**
     * 根据id删除指定会员优惠权限
     *
     * @param id 会员优惠权限id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteMemberDiscountPrivilegeById(Long id);

    /**
     * 批量删除会员优惠权限
     *
     * @param ids 会员优惠权限id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteMemberDiscountPrivilegeBatchByIds(Long[] ids);

    /**
     * 根据id更新指定会员优惠权限信息
     *
     * @param memberDiscountPrivilege 会员优惠权限实体
     * @param id                      会员优惠权限id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateMemberDiscountPrivilegeById(MemberDiscountPrivilege memberDiscountPrivilege, Long id);

    /**
     * 添加会员优惠权限
     *
     * @param memberDiscountPrivilege 会员优惠权限实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addMemberDiscountPrivilege(MemberDiscountPrivilege memberDiscountPrivilege);

}

