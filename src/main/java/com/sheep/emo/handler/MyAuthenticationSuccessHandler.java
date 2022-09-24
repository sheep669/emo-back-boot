package com.sheep.emo.handler;

import com.sheep.emo.pojo.SystemLoginLog;
import com.sheep.emo.response.Result;
import com.sheep.emo.service.SystemLoginLogService;
import com.sheep.emo.service.UserService;
import com.sheep.emo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.Date;


/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/17 18:44
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends JsonResult implements AuthenticationSuccessHandler {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SystemLoginLogService systemLoginLogService;
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Object userinfo = redisUtil.getValueByKey(authentication.getName());
        System.out.println("m==============================m");
        System.out.println(authentication.getName());
        redisUtil.setValueByKey("username", authentication.getName());
        System.out.println("m==============================m");
        SystemLoginLog systemLoginLog = new SystemLoginLog();
        systemLoginLog.setUsername(authentication.getName());
        systemLoginLog.setLoginTime(new Date(System.currentTimeMillis()));
        systemLoginLog.setCompanyName(userService.findPersonalInformation(authentication.getName()).getPhoneNumber());
        systemLoginLog.setIpAddress(Inet4Address.getLocalHost().getHostAddress());
        systemLoginLogService.addSystemLoginLog(systemLoginLog);
        Result result = Result.ok().message("登录成功").data("token", userinfo);
        this.WriteJSON(request, response, result);
    }
}

