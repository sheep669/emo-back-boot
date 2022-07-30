package com.sheep.emo;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.sheep.emo.pojo.GroupBuyingOrganizer;
import com.sheep.emo.service.GroupBuyingOrganizerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmoBackBootApplicationTests {

    @Autowired
    private GroupBuyingOrganizerService groupBuyingOrganizerService;

    @Test
    void contextLoads() {
        List<GroupBuyingOrganizer> groupBuyingOrganizerList = groupBuyingOrganizerService.getGroupBuyingOrganizerList();
        String s1 = JSON.toJSONString(groupBuyingOrganizerList);
        System.out.println(StrUtil.toUnderlineCase(s1));
    }

}
