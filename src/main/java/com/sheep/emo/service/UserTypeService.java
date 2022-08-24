package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.UserType;
import org.springframework.stereotype.Service;

/**
 * 操作用户类型的服务接口
 *
 * @author sheep669
 * @created at 2022-08-23
 */

@Service
public interface UserTypeService {


    /**
     * 分页获得用户类型列表或者查询并分页获得用户类型列表
     *
     * @param current  当前第几页
     * @param size     每页条数
     * @param userType 查询条件
     * @return Page<UserType>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<UserType> searchOrGetUserTypeList(int current, int size, UserType userType);

    /**
     * 根据id删除指定用户类型
     *
     * @param id 用户类型id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteUserTypeById(Long id);

    /**
     * 批量删除用户类型
     *
     * @param ids 用户类型id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteUserTypeBatchByIds(Long[] ids);

    /**
     * 根据id更新指定用户类型信息
     *
     * @param userType 用户类型实体
     * @param id       用户类型id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateUserTypeById(UserType userType, Long id);

    /**
     * 添加用户类型
     *
     * @param userType 用户类型实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addUserType(UserType userType);

}

