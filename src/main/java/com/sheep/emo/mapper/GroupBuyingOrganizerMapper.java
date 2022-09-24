package com.sheep.emo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheep.emo.pojo.GroupBuyingOrganizer;

import java.util.List;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/29 11:40
 */
public interface GroupBuyingOrganizerMapper extends BaseMapper<GroupBuyingOrganizer> {
    List<GroupBuyingOrganizer> getGroupBuyingOrganizerRank();
}
