package com.kb.video.controller;

import com.kb.common.base.BaseResponse;
import com.kb.common.utils.AssertUtil;
import com.kb.video.dao.mapper.VideoMapper;
import com.kb.video.pojo.VideoInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author syg
 * @version 1.0
 */
@RestController
@Api("排行相关")
@Slf4j
public class RateController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private VideoMapper videoMapper;
    @GetMapping("/top")
    @ApiOperation("top10")
    public BaseResponse top() {
        Set<String> set = redisTemplate.opsForZSet().reverseRange("topZSet", 1, 10);
        List<VideoInfo> list = new ArrayList<>();
        //  log.info(set.toString());
        AssertUtil.assertEmptyCollection(set,"暂无排行信息");
        for(String id:set){
            list.add(videoMapper.selectById(id));
            log.info(id);
        }
        return BaseResponse.success(list);
    }

    @GetMapping("/topCategory")
    @ApiOperation("分类top10")
    public BaseResponse top(Integer category) {
        redisTemplate.opsForZSet().reverseRange("topZSet" + category, 1, 10);
        return null;
    }

}
