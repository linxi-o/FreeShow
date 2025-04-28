package com.kb.user.dao;

import com.kb.user.pojo.userMoment.UserMoment;
import org.springframework.stereotype.Repository;

/**
 * @author syg
 * @version 1.0
 */
@Repository
public interface UserMomentMapper {
    /**
     * 添加动态,返回动态id
     * @param userMoment
     * @return
     */
    Long addUserMoments(UserMoment userMoment);

    /**
     * 详情
     * @param count
     * @return
     */
    UserMoment detail(Long count);
}
