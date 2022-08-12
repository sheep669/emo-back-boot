package com.sheep.emo.controller;

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
import com.sheep.emo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : sheep669
 * @description : 登录相关操作的控制器
 * @created at 2022/7/17 18:14
 */
@RestController
public class LoginController {
    /**
     * 菜单接口
     */
    @Autowired
    private MenuMapper menuMapper;
    /**
     * 用户接口
     */
    @Autowired
    private UserMapper userMapper;
    /**
     * redis工具类
     */
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 密码生成
     */
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
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        //图形验证码写出，可以写出到文件，也可以写出到流
        ServletOutputStream outputStream = response.getOutputStream();
        lineCaptcha.write(outputStream);
        String code = lineCaptcha.getCode();
        redisUtil.setValueByKey(request.getSession().getId(), code);
    }

    /**
     * 获取菜单数据
     *
     * @return java.util.List<com.sheep.emo.pojo.Menu>
     * @author sheep669
     * @created at 2022/8/5 9:49
     */
    @GetMapping("/get_menu_data")
    public List<Menu> getMenuData() {
        List<Menu> menu = menuMapper.getMenuByRole((String) redisUtil.getValueByKey("role"));
        redisUtil.delete("role");
        return menu;
    }

    /**
     * 注册
     *
     * @param request 请求对象
     * @return com.sheep.emo.response.Result
     * @author sheep669
     * @created at 2022/8/5 9:49
     */
    @PostMapping("/register")
    public Result doRegister(HttpServletRequest request) {
        String uName = request.getParameter("uName");
        String uPwd1 = request.getParameter("uPwd1");
        String uPwd2 = request.getParameter("uPwd2");
        if (StrUtil.isBlank(uName)) {
            return Result.error(ResultCode.USERNAME_IS_BLANK);
        }
        if (StrUtil.isBlank(uPwd1)) {
            return Result.error(ResultCode.PASSWORD_IS_BLANK);
        }
        if (StrUtil.isBlank(uPwd2)) {
            return Result.error(ResultCode.PASSWORD2_IS_BLANK);
        }
        if (uPwd1.equals(uPwd2)) {
            User user = new User();
            user.setUsername(uName);
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUsername, uName);
            User u = userMapper.selectOne(queryWrapper);
            if (ObjectUtil.isNotNull(u)) {
                return Result.error(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
            } else {
                user.setPassword(passwordEncoder.encode(uPwd2));
                int insert = userMapper.insert(user);
                if (insert > 0) {
                    return Result.ok().message("注册成功");
                }
            }
        } else {
            return Result.error(ResultCode.PASSWORD_NOT_MATCH);
        }
        return Result.error(ResultCode.UNKNOWN_ERROR);
    }
}

