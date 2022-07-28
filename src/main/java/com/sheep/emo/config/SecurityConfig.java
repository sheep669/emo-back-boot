package com.sheep.emo.config;

import com.sheep.emo.filter.AccountVerifyFilter;
import com.sheep.emo.filter.CaptchaVerifyFilter;
import com.sheep.emo.filter.EncodeHandleFilter;
import com.sheep.emo.filter.JwtTokenVerifyFilter;
import com.sheep.emo.handler.*;
import com.sheep.emo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;

/**
 * @author : sheep669
 * @description : spring security 的 配置类
 * @created at 2022/7/11 16:50
 */
@Configuration
public class SecurityConfig {

    /**
     * 注入验证码验证过滤器
     *
     * @author sheep669
     * @created at 2022/7/18 14:50
     */
    @Autowired
    private CaptchaVerifyFilter captchaVerifyFilter;

    /**
     * Token验证是否存在过滤器
     *
     * @author sheep669
     * @created at 2022/7/18 14:56
     */
    @Autowired
    private JwtTokenVerifyFilter jwtTokenVerifyFilter;

    /**
     * 配置加密方式, 替换默认的 PasswordEncoder 采用 spring security 提供的 BCryptPasswordEncoder
     *
     * @return PasswordEncoder
     * @author sheep669
     * @created at 2022/7/17 18:34
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // return NoOpPasswordEncoder.getInstance();  让密码明文存储也可登录 过时了
    }

    /**
     * 注入UserDetailsService返回自定义的实现类
     *
     * @return UserDetailsService
     * @author sheep669
     * @created at 2022/7/17 21:18
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    /**
     * 登录成功处理器
     *
     * @author sheep669
     * @created at 2022/7/17 18:55
     */

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    /**
     * 登录失败处理器
     *
     * @author sheep669
     * @created at 2022/7/17 18:55
     */

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 用户未登录处理器
     *
     * @author sheep669
     * @created at 2022/7/17 18:55
     */

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    /**
     * 权限不足处理器
     *
     * @author sheep669
     * @created at 2022/7/17 18:55
     */

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    /**
     * 注销处理器
     *
     * @author sheep669
     * @created at 2022/7/17 18:55
     */

    @Autowired
    private MyLogoutHandler myLogoutHandler;

    /**
     * 注销成功处理器
     *
     * @author sheep669
     * @created at 2022/7/17 18:55
     */

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;


    @Autowired
    private AccountVerifyFilter accountVerifyFilter;


    @Autowired
    private EncodeHandleFilter encodeHandleFilter;

    @Autowired
    private MyExpiredSessionStrategy myExpiredSessionStrategy;


    /**
     * 配置过滤拦截
     * anonymous() 允许匿名用户访问,不允许已登入用户访问
     * permitAll() 不管登入,不登入 都能访问
     *
     * @param http 请求配置
     * @return SecurityFilterChain
     * @author sheep669
     * @created at 2022/7/17 18:35
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/captcha").permitAll()
                .antMatchers("/get_menu_data").anonymous()
                .antMatchers("/register").anonymous()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(captchaVerifyFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtTokenVerifyFilter, CaptchaVerifyFilter.class)
                .addFilterBefore(accountVerifyFilter, JwtTokenVerifyFilter.class)
                .addFilterBefore(encodeHandleFilter, AccountVerifyFilter.class)
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and().exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                .accessDeniedHandler(myAccessDeniedHandler)
                .and().logout().permitAll()
                .addLogoutHandler(myLogoutHandler)
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and().sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(myExpiredSessionStrategy)
                .and().and().csrf().disable()
                .cors().configurationSource(corsConfigurationSource());

        return http.build();
    }


    /**
     * 注入授权提供者, 设置授权实现类和密码校验
     *
     * @return DaoAuthenticationProvider
     * @author sheep669
     * @created at 2022/7/17 21:19
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * 配置跨域
     *
     * @return CorsConfigurationSource
     * @author sheep669
     * @created at 2022/7/22 14:01
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // 新建一个跨域配置
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许跨域访问的域名 所有
        configuration.addAllowedOrigin("*");
        // 允许访问的方法名 所有
        configuration.addAllowedMethod("*");
        // 允许服务端访问客户端的请求头 所有
        configuration.addAllowedHeader("*");
        // 跨域允许时间 3秒
        configuration.setMaxAge(Duration.ofMillis(3000));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 允许跨域访问的请求 所有请求 /**
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

