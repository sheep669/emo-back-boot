package com.sheep.emo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheep.emo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/17 15:30
 */
public interface UserMapper extends BaseMapper<User> {
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
     * 查询一个用户
     *
     * @param username 用户名
     * @return com.sheep.emo.pojo.User
     * @author sheep669
     * @created at 2022/9/24 11:40
     */
    User findUserByUsername(String username);

    int grantVip(Long id);

    int grantSVip(Long id);

    int grantAdmin(Long id);

    int grantGroupBuyingOrganizer(Long id);

    int grantShopOwners(Long id);

    int uploadAvatarById(@Param("avatar") String avatar, @Param("id") Long id);

    String findUserByStatus(String username);

    List<String> findAvatar();
}
