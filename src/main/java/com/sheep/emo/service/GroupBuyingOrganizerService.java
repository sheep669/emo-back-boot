package com.sheep.emo.service;

import com.sheep.emo.pojo.GroupBuyingOrganizer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/29 11:20
 */
@Service
public interface GroupBuyingOrganizerService {
    /**
     * 获得团长列表
     *
     * @return List<GroupBuyingOrganizer>
     * @author sheep669
     * @created at 2022/7/29 11:51
     */
    List<GroupBuyingOrganizer> getGroupBuyingOrganizerList();
}

