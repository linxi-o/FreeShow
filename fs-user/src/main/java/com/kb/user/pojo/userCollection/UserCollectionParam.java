package com.kb.user.pojo.userCollection;

import com.kb.common.base.BaseQuery;

/**
 * @author syg
 * @version 1.0
 */
public class UserCollectionParam extends BaseQuery {
    private Long userId;

    private Long collectionId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

}
