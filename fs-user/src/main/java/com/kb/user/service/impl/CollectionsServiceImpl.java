package com.kb.user.service.impl;

import com.kb.common.base.BaseResponse;
import com.kb.common.utils.AssertUtil;
import com.kb.user.dao.CollectionsMapper;
import com.kb.user.pojo.collections.Collections;
import com.kb.user.service.api.CollectionsService;
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
public class CollectionsServiceImpl implements CollectionsService {

    @Resource
    private CollectionsMapper collectionsMapper;

    @Override
    public BaseResponse list(Long userId) {
        List<Collections> list =collectionsMapper.list(userId);
        return BaseResponse.success(list,list.size());
    }

    @Override
    public BaseResponse add(Collections collections) {
        Integer count=collectionsMapper.add(collections);
        AssertUtil.assertNotEquals(1,count,"添加收藏夹操作失败,请重试!");
        return BaseResponse.success("添加收藏夹成功!");
    }

    @Override
    public BaseResponse update(Collections collections) {
        AssertUtil.isTrue(collections.getType()==0,"公共类型不能更新");
        Integer count=collectionsMapper.update(collections);
        AssertUtil.assertNotEquals(1,count,"更新操作失败,请重试!");
        return BaseResponse.success("更新成功!");
    }

    @Override
    public BaseResponse delete(Long id) {
        AssertUtil.assertNull(id,"分组不存在");
        Collections temp=collectionsMapper.detail(id);
        AssertUtil.assertNull(temp,"分组不存在");
        AssertUtil.isTrue(temp.getType()==0,"公共类型不能删除");

        Integer count=collectionsMapper.delete(id);
        AssertUtil.assertNotEquals(1,count,"删除操作失败,请重试!");
        return BaseResponse.success("删除成功!");
    }

    @Override
    public BaseResponse detail(Long id) {
        Collections collections=collectionsMapper.detail(id);
        return BaseResponse.success(collections);
    }
}
