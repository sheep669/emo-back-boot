package com.sheep.emo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.GroupBuyingOrganizerMapper;
import com.sheep.emo.mapper.MenuMapper;
import com.sheep.emo.pojo.GroupBuyingOrganizer;
import com.sheep.emo.pojo.Menu;
import com.sheep.emo.service.GroupBuyingOrganizerService;
import com.sheep.emo.utils.ValidatorUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class EmoBackBootApplicationTests {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private GroupBuyingOrganizerService groupBuyingOrganizerService;
    @Autowired
    private GroupBuyingOrganizerMapper groupBuyingOrganizerMapper;

    @Test
    void contextLoads() {
        UpdateWrapper<GroupBuyingOrganizer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 1);
        updateWrapper.set("audit_status", "2");
        int update = groupBuyingOrganizerMapper.update(null, updateWrapper);
        if (update > 0) {
            System.out.println("update is ok");
        }
    }

    @Test
    public void testPage() {
        //当前第2页，查3条
        Page<GroupBuyingOrganizer> groupBuyingOrganizerPage = new Page<>(2, 3);
        Page<GroupBuyingOrganizer> page = groupBuyingOrganizerMapper.selectPage(groupBuyingOrganizerPage, null);
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    @Test
    public void testUpdate() {
        GroupBuyingOrganizer groupBuyingOrganizer = new GroupBuyingOrganizer();
        groupBuyingOrganizer.setPhoneNumber("17314003693");
        groupBuyingOrganizer.setRemark("客流少");
        groupBuyingOrganizerService.updateGroupBuyingOrganizerById(groupBuyingOrganizer, 2L);
    }

    @Test
    public void testAdd() {
        GroupBuyingOrganizer groupBuyingOrganizer = new GroupBuyingOrganizer();
        groupBuyingOrganizer.setPhoneNumber("17314003693");
        groupBuyingOrganizer.setRemark("客流少");
        groupBuyingOrganizerMapper.insert(groupBuyingOrganizer);
    }

    @Test
    public void testMapper() {
        GroupBuyingOrganizer groupBuyingOrganizer = new GroupBuyingOrganizer();
        groupBuyingOrganizer.setPhoneNumber("12314003693");
        groupBuyingOrganizer.setRemark("是好人ya");
        groupBuyingOrganizerMapper.insert(groupBuyingOrganizer);
    }

    @Test
    public void testValidator() {
        GroupBuyingOrganizer groupBuyingOrganizer = new GroupBuyingOrganizer();
        groupBuyingOrganizer.setPhoneNumber("152140039");
        groupBuyingOrganizer.setRemark("是好人ya");
        Map<String, Object> map = ValidatorUtil.valid(groupBuyingOrganizer);
        System.out.println(map.size());
        System.out.println(JSONUtil.toJsonStr(map));
    }

    @Test
    public void testDelete() {
        int i = groupBuyingOrganizerService.deleteGroupBuyingOrganizerById(5L);
        if (i > 0) {
            System.out.println("删除成功");
        }
    }

    @Test
    public void testGetMenu() {
        List<Menu> admin = menuMapper.getMenuByRole("admin");
        System.out.println(JSONUtil.toJsonStr(admin));
    }
}
