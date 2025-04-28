package com.kb.oauth.dao.feign;

import com.alibaba.fastjson.JSONObject;
import com.kb.common.base.BaseResponse;
import com.kb.oauth.pojo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author syg
 */
@FeignClient(name = "user")
public interface UserInfoFeign {

    @PostMapping("/userinfo")
    public BaseResponse add(@RequestBody JSONObject jsonObject);
}
