package com.sheep.emo.handler;

import com.sheep.emo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : sheep669
 * @description : 注销处理器
 * @created at 2022/7/17 22:40
 */
@Component("myLogoutHandler")
public class MyLogoutHandler extends JsonResult implements LogoutHandler {
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        redisUtil.delete(authentication.getName());
        SecurityContextHolder.clearContext();
    }
}

