package com.kb.user.service.api;

import com.kb.common.base.BaseResponse;
import com.kb.user.pojo.user.User;


/**
 * @author syg
 * @version 1.0.0
 * @description
 */
public interface UserService {

    /**
     * 绑定详情
     * @param id
     * @return
     */
    BaseResponse detail(Long id);

    /**
     * add注册时调用
     * @param user
     * @return
     */
    BaseResponse add(User user);

    /***
     * 更新密码，绑定信息
     * @param user
     * @return
     */
    BaseResponse update(User user);

    /**
     * 注销
     * @param id
     * @return
     */
    BaseResponse delete(Long id);

    /**
     * 根据key（手机号或邮箱获取user）
     * @param key
     * @return
     */
    BaseResponse detailByKey(String key);
}
