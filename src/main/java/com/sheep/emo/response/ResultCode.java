package com.sheep.emo.response;

/**
 * 响应码规范
 *
 * @author sheep669
 * @created at 2022/7/29 8:32
 */
public enum ResultCode {

    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 登陆相关 */
    USERNAME_IS_BLANK(1001, "用户名不能为空"),
    PASSWORD_IS_BLANK(1002, "密码不能为空"),
    PASSWORD2_IS_BLANK(1003, "确认密码不能为空"),
    PASSWORD_NOT_MATCH(1004, "两次密码不一致"),
    TOKEN_IS_BLANK(1005, "Token为空"),
    TOKEN_IS_EXPIRED(1006, "Token已失效"),
    VERIFY_CODE_FAIL(1007, "验证码错误"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "用户名或密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "已经在另一台机器登录,无需重复登陆"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限");


    private final Integer code;

    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
