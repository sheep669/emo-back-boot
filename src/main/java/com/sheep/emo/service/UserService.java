package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.User;

import java.util.List;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/8/18 21:11
 */
public interface UserService {

    /**
     * 查询一个用户
     *
     * @param username 用户名
     * @return com.sheep.emo.pojo.User
     * @author sheep669
     * @created at 2022/9/24 11:38
     */
    User findUserByUsername(String username);


    /**
     * 获取个人资料页
     *
     * @param username 用户名
     * @return com.sheep.emo.pojo.User
     * @author sheep669
     * @created at 2022/8/18 21:10
     */
    User findPersonalInformation(String username);


    /**
     * 分页获得用户列表或者查询并分页获得用户列表
     *
     * @param current 当前第几页
     * @param size    每页条数
     * @param user    查询条件
     * @return Page<User>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<User> searchOrGetUserList(int current, int size, User user);

    /**
     * 根据id删除指定用户
     *
     * @param id 用户id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteUserById(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 用户id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteUserBatchByIds(Long[] ids);

    /**
     * 根据id更新指定用户信息
     *
     * @param user 用户实体
     * @param id   用户id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateUserById(User user, Long id);

    /**
     * 添加用户
     *
     * @param user 用户实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addUser(User user);

    int grantVip(Long id);

    int grantSVip(Long id);

    int grantAdmin(Long id);

    int grantGroupBuyingOrganizer(Long id);

    int grantShopOwners(Long id);

    int uploadAvatarById(String avatar, Long id);

    List<String> findAvatar();
}

