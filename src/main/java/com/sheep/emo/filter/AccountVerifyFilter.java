package com.sheep.emo.filter;

import cn.hutool.core.util.StrUtil;
import com.sheep.emo.constant.Constant;
import com.sheep.emo.mapper.UserMapper;
import com.sheep.emo.response.Result;
import com.sheep.emo.response.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import cn.hutool.json.JSONUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/19 9:13
 */
@Component("accountVerifyFilter")
public class AccountVerifyFilter extends OncePerRequestFilter {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 一般通过表单和链接传递的参数使用getParameter获取
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (request.getServletPath().equals(Constant.LOGIN_URL) && request.getMethod().equalsIgnoreCase(Constant.POST_REQUEST_METHOD)) {
            if (StrUtil.isBlank(username)) {
                Result result = Result.error(ResultCode.USERNAME_IS_BLANK);
                response.getWriter().write(JSONUtil.toJsonStr(result));
            } else if (StrUtil.isBlank(password)) {
                Result result = Result.error(ResultCode.PASSWORD_IS_BLANK);
                response.getWriter().write(JSONUtil.toJsonStr(result));
            } else if (userMapper.findUserByStatus(username).equals(Constant.USER_IS_LOCKED)) {
                Result result = Result.error(ResultCode.USER_ACCOUNT_LOCKED);
                response.getWriter().write(JSONUtil.toJsonStr(result));
            } else {
                // 通行
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}

