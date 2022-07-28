package com.sheep.emo.handler;

import com.sheep.emo.response.Result;
import com.sheep.emo.response.ResultCode;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author : sheep669
 * @description : 当账户被踢下线的时候如何处理
 * @created at 2022/7/17 21:32
 */
@Component("myExpiredSessionStrategy")
public class MyExpiredSessionStrategy extends JsonResult implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        Result result = Result.error(ResultCode.USER_ACCOUNT_USE_BY_OTHERS);
        this.WriteJSON(event.getRequest(), event.getResponse(), result);
    }
}
