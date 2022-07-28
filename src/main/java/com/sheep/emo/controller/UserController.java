package com.sheep.emo.controller;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/17 18:14
 */

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sheep.emo.mapper.MenuMapper;
import com.sheep.emo.mapper.UserMapper;
import com.sheep.emo.pojo.Menu;
import com.sheep.emo.pojo.User;
import com.sheep.emo.response.Result;
import com.sheep.emo.response.ResultCode;
import com.sheep.emo.utils.JsonUtil;
import com.sheep.emo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取验证码
     *
     * @param response 响应对象
     * @return void
     * @author sheep669
     * @created at 2022/7/23 10:08
     */
    @RequestMapping("/captcha")
    public void getCaptcha(HttpServletResponse response) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        //图形验证码写出，可以写出到文件，也可以写出到流
        ServletOutputStream outputStream = response.getOutputStream();
        lineCaptcha.write(outputStream);
        String code = lineCaptcha.getCode();
        redisUtil.setValueByKey("verCode", code);
    }

    /**
     * 获取菜单数据
     *
     * @return String
     * @author sheep669
     * @created at 2022/7/23 10:09
     */
    @GetMapping("/get_menu_data")
    public String getMenuData() {
        List<Menu> menu = menuMapper.getMenuByRole((String) redisUtil.getValueByKey("role"));
        return JsonUtil.toUnderlineJsonString(menu);
    }

    @PostMapping("/register")
    public String doRegister(HttpServletRequest request) {
        String uName = request.getParameter("uName");
        String uPwd1 = request.getParameter("uPwd1");
        String uPwd2 = request.getParameter("uPwd2");
        if (StrUtil.isBlank(uName)) {
            Result res = Result.error(ResultCode.USERNAME_IS_BLANK);
            return JsonUtil.toUnderlineJsonString(res);
        }
        if (StrUtil.isBlank(uPwd1)) {
            Result res = Result.error(ResultCode.PASSWORD_IS_BLANK);
            return JsonUtil.toUnderlineJsonString(res);
        }
        if (StrUtil.isBlank(uPwd2)) {
            Result res = Result.error(ResultCode.PASSWORD2_IS_BLANK);
            return JsonUtil.toUnderlineJsonString(res);
        }
        if (uPwd1.equals(uPwd2)) {
            User user = new User();
            user.setUsername(uName);
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUsername, uName);
            User u = userMapper.selectOne(queryWrapper);
            if (ObjectUtil.isNotNull(u)) {
                Result res = Result.error(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
                return JsonUtil.toUnderlineJsonString(res);
            } else {
                user.setPassword(passwordEncoder.encode(uPwd2));
                user.setRole("user");
                int insert = userMapper.insert(user);
                if (insert > 0) {
                    Result res = Result.ok().message("注册成功");
                    return JsonUtil.toUnderlineJsonString(res);
                }
            }
        } else {
            Result res = Result.error(ResultCode.PASSWORD_NOT_MATCH);
            return JsonUtil.toUnderlineJsonString(res);
        }
        return null;
    }

    @GetMapping("/get_data")
    public String getTableData() {
        return "this is data";
    }
}

