package com.kb.user.controller;

import com.kb.common.base.BaseResponse;
import com.kb.user.pojo.user.User;
import com.kb.user.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户的注册，添加，删除，更新，详细信息
 * @author syg
 * @version 1.0.0
 * @description
 */
@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/user/{key}")
    public BaseResponse detailByKey(@PathVariable String key){
        return userService.detailByKey(key);
    }

    @GetMapping("/user")
    public BaseResponse detail(Long id){
        return userService.detail(id);
    }

    @PostMapping("/user")
    public BaseResponse add(@RequestBody  User user){
        return userService.add(user);
    }

    @PutMapping("/user")
    public BaseResponse update(@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping("/user")
    public BaseResponse delete(Long id){
        return userService.delete(id);
    }
}
