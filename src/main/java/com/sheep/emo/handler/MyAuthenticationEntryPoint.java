package com.sheep.emo.handler;

import com.sheep.emo.response.Result;
import com.sheep.emo.response.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : sheep669
 * @description : 匿名用户访问无权限资源时的异常
 * @created at 2022/7/17 22:19
 */
@Component("myAuthenticationEntryPoint")
public class MyAuthenticationEntryPoint extends JsonResult implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result error = Result.error(ResultCode.USER_NOT_LOGIN);
        this.WriteJSON(request, response, error);
    }
}

