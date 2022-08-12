package com.sheep.emo.handler;

import cn.hutool.json.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/17 22:10
 */
public abstract class JsonResult {
    protected void WriteJSON(HttpServletRequest request,
                             HttpServletResponse response,
                             Object data) throws IOException, ServletException {
        //这里很重要，否则页面获取不到正常的JSON数据集
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        response.setHeader("Access-Control-Allow-Method", "POST,GET");
        //输出JSON
        PrintWriter out = response.getWriter();

        out.write(JSONUtil.toJsonStr(data));
        out.flush();
        out.close();
    }
}

