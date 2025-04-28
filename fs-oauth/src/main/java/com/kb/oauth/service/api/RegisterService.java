package com.kb.oauth.service.api;

import com.kb.common.base.BaseResponse;
import com.kb.oauth.vo.params.RegisterParam;

/**
 * @author syg
 */
public interface RegisterService {
    /**
     * 注册
     * @param registerParam
     * @return
     */
    BaseResponse register(RegisterParam registerParam);
}
