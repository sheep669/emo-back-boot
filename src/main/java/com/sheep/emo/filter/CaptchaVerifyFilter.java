package com.sheep.emo.filter;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.sheep.emo.constant.Constant;
import com.sheep.emo.response.Result;
import com.sheep.emo.response.ResultCode;
import com.sheep.emo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/13 22:13
 */
@Component("captchaVerifyFilter")
public class CaptchaVerifyFilter extends OncePerRequestFilter {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().equals(Constant.LOGIN_URL) && request.getMethod().equalsIgnoreCase(Constant.POST_REQUEST_METHOD)) {
            //校验验证码
            String pvc = request.getParameter("verCode");
            if (StrUtil.isBlank(pvc)) {
                // 响应验证码错误到前端
                Result result = Result.error(ResultCode.VERIFY_IS_BLANK);
                response.getWriter().write(JSONUtil.toJsonStr(result));
            } else {
                String rvc = (String) redisUtil.getValueByKey(request.getSession().getId());
                if (pvc.equals(rvc)) {
                    redisUtil.delete(request.getSession().getId());
                    //放行
                    filterChain.doFilter(request, response);
                } else {
                    // 响应验证码错误到前端
                    Result result = Result.error(ResultCode.VERIFY_CODE_FAIL);
                    response.getWriter().write(JSONUtil.toJsonStr(result));
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}

