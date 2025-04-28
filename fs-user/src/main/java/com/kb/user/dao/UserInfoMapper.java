package com.kb.user.dao;

import com.kb.user.pojo.userInfo.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author syg
 * @version 1.0
 */
@Repository
public interface UserInfoMapper {
    /**
     * 详情
     * @param id
     * @return UserInfo
     */
    UserInfo detail(Long id);

    /**
     * 添加
     * @param userInfo
     * @return
     */
    Integer add(UserInfo userInfo);

    /**
     * 更新
     * @param userInfo
     * @return
     */
    Integer update(UserInfo userInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer delete(Long id);
}
