package com.sheep.emo.controller;

import com.sheep.emo.pojo.GroupBuyingOrganizer;
import com.sheep.emo.service.GroupBuyingOrganizerService;
import com.sheep.emo.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/29 11:47
 */
@RestController
public class GroupBuyingOrganizerController {

    @Autowired
    private GroupBuyingOrganizerService groupBuyingOrganizerService;

    @GetMapping("/getGroupBuyingOrganizerList")
    public String getGroupBuyingOrganizerList() {
        List<GroupBuyingOrganizer> groupBuyingOrganizerList = groupBuyingOrganizerService.getGroupBuyingOrganizerList();
        return JsonUtil.toUnderlineJsonStringWithTimeFormat(groupBuyingOrganizerList);
    }
}

