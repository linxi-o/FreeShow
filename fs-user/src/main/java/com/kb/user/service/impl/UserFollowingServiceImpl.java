package com.kb.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kb.common.base.BaseResponse;
import com.kb.common.utils.AssertUtil;
import com.kb.user.dao.UserFollowingMapper;
import com.kb.user.pojo.userFollowing.UserFollowing;
import com.kb.user.pojo.userFollowing.UserFollowingParam;
import com.kb.user.service.api.UserFollowingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 关注用户，取关，查询关注列表，关注，被关注总数
 * @author syg
 * @version 1.0.0
 * @description
 */
@Service
public class UserFollowingServiceImpl implements UserFollowingService {

    @Resource
    private UserFollowingMapper userFollowingMapper;

    @Override
    public BaseResponse add(UserFollowing userFollowing) {
        Integer count=userFollowingMapper.add(userFollowing);
        AssertUtil.assertNotEquals(1,count,"关注操作失败,请重试!");
        return BaseResponse.success("关注成功!");
    }

    @Override
    public BaseResponse delete(Long id) {
        Integer count=userFollowingMapper.delete(id);
        AssertUtil.assertNotEquals(1,count,"取关操作失败,请刷新重试!");
        return BaseResponse.success("取关成功!");
    }

    @Override
    public BaseResponse list(UserFollowingParam userFollowingParam) {
        List<UserFollowing> list=userFollowingMapper.list(userFollowingParam);
        PageHelper.startPage(userFollowingParam.getPage(),userFollowingParam.getLimit());
        PageInfo<UserFollowing> pageInfo=new PageInfo<>(list);
        return BaseResponse.success(pageInfo.getList(),pageInfo.getList().size());
    }

    @Override
    public BaseResponse fans(Long followingId) {
        List<UserFollowing> fans=userFollowingMapper.fans(followingId);
        return BaseResponse.success(fans,fans.size());
    }

    @Override
    public BaseResponse fansCount(Long followingId) {
        Long count=userFollowingMapper.fansCount(followingId);
        return BaseResponse.success(count);
    }

    @Override
    public BaseResponse followingCount(Long userId) {
        Long count=userFollowingMapper.followingCount(userId);
        return BaseResponse.success(count);
    }
}
