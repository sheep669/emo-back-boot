package com.sheep.emo.filter;

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
 * @created at 2022/7/13 13:22
 */
@Component("jwtTokenVerifyFilter")
public class JwtTokenVerifyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }


    // @Autowired
    // private RedisUtil redisUtil;
    //
    // @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //     if (request.getServletPath().equals(Constant.LOGIN_URL) && request.getMethod().equalsIgnoreCase(Constant.POST_REQUEST_METHOD)) {
    //         filterChain.doFilter(request, response);
    //     } else if (request.getServletPath().equals(Constant.CAPTCHA_URL)) {
    //         filterChain.doFilter(request, response);
    //     } else if (request.getServletPath().equals(Constant.GET_MENUS_URL)) {
    //         filterChain.doFilter(request, response);
    //     } else if (request.getServletPath().equals(Constant.REGISTER_URL)) {
    //         filterChain.doFilter(request, response);
    //     } else {
    //         if (StrUtil.isBlank(request.getHeader(Constant.TOKEN_KEY))) {
    //             Result res = Result.error(ResultCode.TOKEN_IS_BLANK);
    //             response.getWriter().write(JSONUtil.toJsonStr(res));
    //         } else {
    //             //验证token是否有效
    //             String token = request.getHeader(Constant.TOKEN_KEY);
    //             boolean verify = JWTUtil.verify(token, Constant.TOKEN_VERIFY_KEY.getBytes());
    //             System.out.println(verify);
    //             if (verify) {
    //                 filterChain.doFilter(request, response);
    //             } else {
    //                 Result res = Result.error(ResultCode.TOKEN_IS_EXPIRED);
    //                 response.getWriter().write(JSONUtil.toJsonStr(res));
    //             }
    //         }
    //     }
    // }
}

