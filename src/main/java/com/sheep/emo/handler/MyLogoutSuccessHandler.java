package com.sheep.emo.handler;

import com.sheep.emo.response.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : sheep669
 * @description : 注销成功处理器
 * @created at 2022/7/17 22:45
 */
@Component("myLogoutSuccessHandler")
public class MyLogoutSuccessHandler extends JsonResult implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result result = Result.ok().message("注销成功");
        this.WriteJSON(request, response, result);
    }
}

