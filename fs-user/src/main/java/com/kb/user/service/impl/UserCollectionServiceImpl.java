package com.kb.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kb.common.base.BaseResponse;
import com.kb.common.utils.AssertUtil;
import com.kb.user.dao.UserCollectionMapper;
import com.kb.user.pojo.userCollection.UserCollection;
import com.kb.user.pojo.userCollection.UserCollectionParam;
import com.kb.user.service.api.UserCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author syg
 * @version 1.0
 */
@Service
@Slf4j
public class UserCollectionServiceImpl implements UserCollectionService {

    @Resource
    private UserCollectionMapper userCollectionMapper;

    //添加收藏
    @Override
    public BaseResponse add(UserCollection userCollection) {
        Integer count=userCollectionMapper.add(userCollection);
        AssertUtil.assertNotEquals(1,count,"收藏失败,请重试!");
        return BaseResponse.success("收藏成功!");
    }

    @Override
    public BaseResponse delete(Long id) {
        Integer count=userCollectionMapper.delete(id);
        AssertUtil.assertNotEquals(1,count,"取消收藏失败,请刷新重试!");
        return BaseResponse.success("取消收藏成功!");
    }

    @Override
    public BaseResponse list(UserCollectionParam userCollectionParam) {
        List<UserCollection> list=userCollectionMapper.list(userCollectionParam);
        PageHelper.startPage(userCollectionParam.getPage(),userCollectionParam.getLimit());
        PageInfo<UserCollection> pageInfo=new PageInfo<>(list);
        return BaseResponse.success(pageInfo.getList(),pageInfo.getList().size());
    }

    @Override
    public BaseResponse count(Long contentId) {
        Long count=userCollectionMapper.count(contentId);
        return BaseResponse.success(count);
    }

    @Override
    public BaseResponse userCollectCount(Long userId) {
        Long count=userCollectionMapper.userCollectCount(userId);
        return BaseResponse.success(count);
    }
}
