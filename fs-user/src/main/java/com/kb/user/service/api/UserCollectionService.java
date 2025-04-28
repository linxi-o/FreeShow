package com.kb.user.service.api;

import com.kb.common.base.BaseResponse;
import com.kb.user.pojo.userCollection.UserCollection;
import com.kb.user.pojo.userCollection.UserCollectionParam;

/**
 * @author syg
 * @version 1.0
 */
public interface UserCollectionService {
    /**
     * 收藏
     * @param userCollection
     * @return
     */
    BaseResponse add(UserCollection userCollection);

    /**
     * 取消收藏
     * @param id
     * @return
     */
    BaseResponse delete(Long id);

    /**
     * 查看收藏夹中的内容
     * @param userCollectionParam
     * @return
     */
    BaseResponse list(UserCollectionParam userCollectionParam);

    /**
     * 查询视频收藏数
     * @param contentId
     * @return
     */
    BaseResponse count(Long contentId);

    /**
     * 查询用户收藏数
     * @param userId
     * @return
     */
    BaseResponse userCollectCount(Long userId);
}
