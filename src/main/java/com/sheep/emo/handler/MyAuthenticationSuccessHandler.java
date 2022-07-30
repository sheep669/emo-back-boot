package com.sheep.emo.handler;

import com.sheep.emo.response.Result;
import com.sheep.emo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/17 18:44
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends JsonResult implements AuthenticationSuccessHandler {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Result result = Result.ok().message("登录成功").data("token", redisUtil.getValueByKey(authentication.getName()));
        this.WriteJSON(request, response, result);
    }
}

