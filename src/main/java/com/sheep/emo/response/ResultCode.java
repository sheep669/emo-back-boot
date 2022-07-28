package com.sheep.emo.response;

/**
 * @author xiaoge
 * @Description 枚举这个就类似静态类, 目的是指定返回的规范
 * 规定:
 * #200表示成功
 * #1001～1999 区间表示参数错误
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 * #4001～4999 区间表示业务错误
 * #5001～5999 区间表示部门错误
 * #9001～9999 区间表示运行时异常
 * #后面对什么的操作自己在这里注明就行了
 */
public enum ResultCode {

    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000～1999 */
    USERNAME_IS_BLANK(1005, "用户名不能为空"),
    PASSWORD_IS_BLANK(1006, "密码不能为空"),
    PASSWORD2_IS_BLANK(1009, "确认密码不能为空"),
    PASSWORD_NOT_MATCH(1010, "两次密码不一致"),
    TOKEN_IS_BLANK(1007, "Token为空"),
    TOKEN_IS_EXPIRED(1008, "Token已失效"),

    VERIFY_CODE_FAIL(1011, "验证码错误"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "用户名或密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "已经在另一台机器登录，您被迫下线"),
    /* 业务错误 */
    NO_PERMISSION(4001, "没有权限");


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
