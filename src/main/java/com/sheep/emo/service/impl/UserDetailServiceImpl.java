package com.sheep.emo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sheep.emo.constant.Constant;
import com.sheep.emo.mapper.UserMapper;
import com.sheep.emo.pojo.User;
import com.sheep.emo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/17 15:26
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if (ObjectUtil.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在！");
        } else {
            Map<String, Object> map = new HashMap<>(16);
            map.put("userInfo", user.getUsername());
            String token = JWTUtil.createToken(map, Constant.TOKEN_VERIFY_KEY.getBytes());
            redisUtil.setValueByKey(user.getUsername(), token);
            redisUtil.setValueByKey("role", user.getRole());
        }
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority role = new SimpleGrantedAuthority(user.getRole());
        authorities.add(role);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);
    }
}

