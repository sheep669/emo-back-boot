package com.sheep.emo.filter;

import com.sheep.emo.constant.Constant;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : sheep669
 * @description : 乱码统一处理过滤器
 * @created at 2022/7/19 11:30
 */
@Component("encodeHandleFilter")
public class EncodeHandleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getServletPath().equals(Constant.CAPTCHA_URL)) {
            response.setContentType("application/json; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
        }
        filterChain.doFilter(request, response);
    }
}

