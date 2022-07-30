package com.sheep.emo.service.impl;

import com.sheep.emo.mapper.GroupBuyingOrganizerMapper;
import com.sheep.emo.pojo.GroupBuyingOrganizer;
import com.sheep.emo.service.GroupBuyingOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/29 11:36
 */
@Service
public class GroupBuyingOrganizerServiceImpl implements GroupBuyingOrganizerService {

    @Autowired
    private GroupBuyingOrganizerMapper groupBuyingOrganizerMapper;

    @Override
    public List<GroupBuyingOrganizer> getGroupBuyingOrganizerList() {
        return groupBuyingOrganizerMapper.selectList(null);
    }
}

