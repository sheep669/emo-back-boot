package com.sheep.emo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共结果返回
 *
 * @author sheep669
 * @created at 2022/7/29 8:34
 */
@Data
public class Result {
    private Integer code;

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> data = new HashMap<>();

    /**
     * 构造方法私有化,里面的方法都是静态方法
     * 达到保护属性的作用
     */
    private Result() {
    }

    /**
     * 这里是使用链式编程
     */
    public static Result ok() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ResultCode.COMMON_FAIL.getCode());
        result.setMsg(ResultCode.COMMON_FAIL.getMsg());
        return result;
    }

    public static Result error(ResultCode resultCode) {
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setMsg(resultCode.getMsg());
        return result;
    }


    public Result message(String message) {
        this.setMsg(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
