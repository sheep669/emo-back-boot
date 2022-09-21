package com.sheep.emo.service;

import org.springframework.stereotype.Service;

/**
 * @author : sheep669
 * @description : 首页的服务接口
 * @created at 2022/9/8 8:16
 */
@Service
public interface IndexService {
    /**
     * 统计会员数
     *
     * @return int
     * @author sheep669
     * @created at 2022/9/8 8:25
     */
    int getMemberNum();
}
