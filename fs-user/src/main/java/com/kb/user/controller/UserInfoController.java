package com.kb.user.controller;

import com.kb.user.pojo.userInfo.UserInfo;
import com.kb.common.base.BaseResponse;
import com.kb.user.service.api.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户信息模块，查看用户详细信息，添加，更新，删除
 * @author syg
 * @version 1.0
 */
@RestController
@Slf4j
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/userinfo")
    public BaseResponse detail(Long id){
        return userInfoService.detail(id);
    }

    @PostMapping("/userinfo")
    public BaseResponse add(@RequestBody  UserInfo userInfo){
        return userInfoService.add(userInfo);
    }

    @PutMapping("/userinfo")
    public BaseResponse update(@RequestBody  UserInfo userInfo){
        return userInfoService.update(userInfo);
    }

    @DeleteMapping("/userinfo")
    public BaseResponse delete(Long id){
        return userInfoService.delete(id);
    }
}
