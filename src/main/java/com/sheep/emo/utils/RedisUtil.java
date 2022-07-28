package com.sheep.emo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/11 12:30
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setValueByKey(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValueByKey(Object key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void deleteAll() {
        Set keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }
}

