package com.kb.video.controller;


import com.kb.common.base.BaseResponse;
import com.kb.video.pojo.BarrageInfo;
import com.kb.video.service.BarrageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


//弹幕服务
//查询当前播放时间的弹幕，所有弹幕，新增弹幕
@RestController
@Api("barrage 弹幕相关")
public class BarrageController {

    @Resource
    BarrageService barrageService;


    /**
     * 以时间为单位分片获取
     *
     * @param videoId
     * @param offset
     * @return
     */
    @GetMapping("/bar/{videoId}/{offset}")
    @ApiOperation("以时间为单位分片获取dm")
    public BaseResponse getBarrageByTime(@PathVariable("videoId") Long videoId, @PathVariable("offset") int offset) {

        List<BarrageInfo> barrages = barrageService.getBarrageByTime(videoId, offset);

        return BaseResponse.success(barrages);

    }

    /**
     * 获取所有
     *
     * @param videoId
     * @return
     */
    @GetMapping("/bar/{videoId}")
    @ApiOperation("获取所有dm")
    public BaseResponse getAllBarrage(@PathVariable("videoId") Long videoId) {

        List<BarrageInfo> res = barrageService.getAllBarrage(videoId);

        return BaseResponse.success(res);

    }

    /**
     * 添加一条
     *
     * @param videoId
     * @param barrageInfo
     * @return
     */
    @PostMapping("/bar/{videoId}")
    @ApiOperation("添加一条dm")
    public BaseResponse addOneBarrage(@PathVariable("videoId") Long videoId,@RequestBody BarrageInfo barrageInfo) {

        barrageService.addOneBarrage(videoId, barrageInfo);

        return BaseResponse.success(null);


    }
}
