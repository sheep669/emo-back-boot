package com.sheep.emo.utils;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;

/**
 * @author : sheep669
 * @description : JSON统一处理
 * @created at 2022/7/22 17:22
 */
public class JsonUtil {
    /**
     * 将对象转成下划线连接的json字符串
     *
     * @param o 任意非空对象
     * @return java.lang.String
     * @author sheep669
     * @created at 2022/7/22 17:42
     */
    public static String toUnderlineJsonString(Object o) {
        if (ObjectUtil.isNotNull(o)) {
            NameFilter nameFilter = (object, key, value) -> {
                // 进行处理
                return StrUtil.toUnderlineCase(key);
            };
            return JSON.toJSONString(o, nameFilter);
        } else {
            return null;
        }
    }
}

