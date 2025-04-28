package com.kb.user.service.api;

import com.kb.user.pojo.userInfo.UserInfo;
import com.kb.common.base.BaseResponse;

/**
 * @author syg
 * @version 1.0
 */
public interface UserInfoService {

    /**
     * 详情接口
     * @param id
     * @return
     */
    BaseResponse detail(Long id);

    /**
     * 添加接口
     * @param userInfo
     * @return
     */
    BaseResponse add(UserInfo userInfo);

    /**
     * 更新接口
     * @param userInfo
     * @return
     */
    BaseResponse update(UserInfo userInfo);

    /**
     * 删除接口
     * @param id
     * @return
     */
    BaseResponse delete(Long id);
}
