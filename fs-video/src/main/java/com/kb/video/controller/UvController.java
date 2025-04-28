package com.kb.video.controller;

import com.kb.common.base.BaseResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author syg
 * @version 1.0
 */
@RestController
public class UvController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
//    把用戶的視頻ID存入redis,記錄某個視頻被哪些用戶訪問過
    @GetMapping("/hlP")
    public BaseResponse hlP(String videoId, String userId) {
        redisTemplate.opsForHyperLogLog().add("hl" + videoId, userId);
        return BaseResponse.success("success");
    }
//查詢訪問量
    @GetMapping("/hlG")
    public BaseResponse hlG(String videoId) {
        Long res = redisTemplate.opsForHyperLogLog().size("hl" + videoId);
        return BaseResponse.success(res);
    }
}
