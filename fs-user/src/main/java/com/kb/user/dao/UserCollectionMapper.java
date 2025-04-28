package com.kb.user.dao;

import com.kb.user.pojo.userCollection.UserCollection;
import com.kb.user.pojo.userCollection.UserCollectionParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author syg
 * @version 1.0
 */
@Repository
public interface UserCollectionMapper {
    /**
     * 收藏
     * @param userCollection
     * @return
     */
    Integer add(UserCollection userCollection);

    /**
     * 取消收藏
     * @param id
     * @return
     */
    Integer delete(Long id);

    /**
     * 查看收藏夹列表
     * @param userCollectionParam
     * @return
     */
    List<UserCollection> list(UserCollectionParam userCollectionParam);

    /**
     * 数量
     * @param videoId
     * @return
     */
    Long count(Long contentId);

    /**
     * 用户数量
     * @param userId
     * @return
     */
    Long userCollectCount(Long userId);
}
