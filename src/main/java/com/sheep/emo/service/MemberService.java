package com.sheep.emo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.pojo.Member;
import org.springframework.stereotype.Service;

/**
 * 操作会员的服务接口
 *
 * @author sheep669
 * @created at 2022-08-22
 */

@Service
public interface MemberService {


    /**
     * 分页获得会员列表或者查询并分页获得会员列表
     *
     * @param current 当前第几页
     * @param size    每页条数
     * @param member  查询条件
     * @return Page<Member>
     * @author sheep669
     * @created at 2022/8/7 11:21
     */
    Page<Member> searchOrGetMemberList(int current, int size, Member member);

    /**
     * 根据id删除指定会员
     *
     * @param id 会员id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:05
     */
    int deleteMemberById(Long id);

    /**
     * 批量删除会员
     *
     * @param ids 会员id(可传多个)
     * @return int
     * @author sheep669
     * @created at 2022/8/8 7:58
     */
    int deleteMemberBatchByIds(Long[] ids);

    /**
     * 根据id更新指定会员信息
     *
     * @param member 会员实体
     * @param id     会员id
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:19
     */
    int updateMemberById(Member member, Long id);

    /**
     * 添加会员
     *
     * @param member 会员实体
     * @return int
     * @author sheep669
     * @created at 2022/8/1 10:28
     */
    int addMember(Member member);

    /**
     * 加入黑名单
     *
     * @param id 会员id
     * @return int
     * @author sheep669
     * @created at 2022/8/25 18:23
     */
    int addBlacklist(Long id);

    /**
     * 移出黑名单
     *
     * @param id 会员id
     * @return int
     * @author sheep669
     * @created at 2022/8/25 18:23
     */
    int removeBlacklist(Long id);
}

