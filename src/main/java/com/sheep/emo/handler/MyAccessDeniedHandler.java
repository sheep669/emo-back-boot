package com.sheep.emo.handler;

import com.sheep.emo.response.Result;
import com.sheep.emo.response.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : sheep669
 * @description : 权限不足处理器
 * @created at 2022/7/17 22:29
 */
@Component("myAccessDeniedHandler")
public class MyAccessDeniedHandler extends JsonResult implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result error = Result.error(ResultCode.NO_PERMISSION);
        this.WriteJSON(request, response, error);
    }
}

