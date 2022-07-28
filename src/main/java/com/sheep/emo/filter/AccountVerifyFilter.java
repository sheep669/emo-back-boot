package com.sheep.emo.filter;

import cn.hutool.core.util.StrUtil;
import com.sheep.emo.constant.Constant;
import com.sheep.emo.response.Result;
import com.sheep.emo.response.ResultCode;
import com.sheep.emo.utils.JsonUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/19 9:13
 */
@Component("accountVerifyFilter")
public class AccountVerifyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 一般通过表单和链接传递的参数使用getParameter获取
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (request.getServletPath().equals(Constant.LOGIN_URL) && request.getMethod().equalsIgnoreCase(Constant.POST_REQUEST_METHOD)) {
            if (StrUtil.isBlank(username)) {
                Result result = Result.error(ResultCode.USERNAME_IS_BLANK);
                response.getWriter().write(Objects.requireNonNull(JsonUtil.toUnderlineJsonString(result)));
            } else if (StrUtil.isBlank(password)) {
                Result result = Result.error(ResultCode.PASSWORD_IS_BLANK);
                response.getWriter().write(Objects.requireNonNull(JsonUtil.toUnderlineJsonString(result)));
            } else {
                // 通行
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}

