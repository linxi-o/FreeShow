package com.kb.user.controller;

import com.kb.common.base.BaseResponse;
import com.kb.user.pojo.userFollowing.UserFollowing;
import com.kb.user.pojo.userFollowing.UserFollowingParam;
import com.kb.user.service.api.UserFollowingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author syg
 * @version 1.0.0
 * @description
 */
@RestController
public class UserFollowingController {
    @Resource
    private UserFollowingService userFollowingService;

    @PostMapping("following")
    public BaseResponse add(@RequestBody  UserFollowing userFollowing){
        return   userFollowingService.add(userFollowing);
    }

    @DeleteMapping("following")
    public BaseResponse delete(Long id){
        return  userFollowingService.delete(id);
    }

    /**
     * todo 根据userid和groupId查询list建个联合索引，userid查询粉丝列表
     * 分页查询
     * @param userFollowingParam
     * @return
     */
    @GetMapping("following")
    public BaseResponse list(UserFollowingParam userFollowingParam){
        return userFollowingService.list(userFollowingParam);
    }

    @GetMapping("following-fans")
    public BaseResponse fans(Long followingId){
        return userFollowingService.fans(followingId);
    }

    /**
     * todo 索引
     * @param followingId
     * @return
     */
    @GetMapping("/fansCount")
    public BaseResponse fansCount(Long followingId){
        return userFollowingService.fansCount(followingId);
    }

    /**
     * todo 索引
     * @param userId
     * @return
     */
    @GetMapping("/followingCount")
    public BaseResponse followingCount(Long userId){
        return userFollowingService.followingCount(userId);
    }


}
