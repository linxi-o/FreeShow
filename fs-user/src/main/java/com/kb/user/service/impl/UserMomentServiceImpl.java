package com.kb.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kb.common.base.BaseResponse;
import com.kb.common.utils.RocketMQUtil;
import com.kb.user.dao.UserMomentMapper;
import com.kb.user.pojo.userMoment.UserMoment;
import com.kb.user.service.api.UserMomentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author syg
 * @version 1.0
 */
@Service
@Slf4j
public class UserMomentServiceImpl implements UserMomentService {

    @Resource
    private UserMomentMapper userMomentMapper;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public BaseResponse addUserMoments(UserMoment userMoment) {
        userMomentMapper.addUserMoments(userMoment);
        userMoment=userMomentMapper.detail(userMoment.getId());
        //从 Spring 的 applicationContext 容器里，拿到 RocketMQ 的消息生产者对象 momentsProducer。
        DefaultMQProducer producer=(DefaultMQProducer)applicationContext.getBean("momentsProducer");
        //把刚刚补充完整的 userMoment 对象，转成 JSON 字符串，然后再转成字节流，封装成一条 RocketMQ 的消息，发送到 Topic-Moments 这个主题上。
        Message msg=new Message("Topic-Moments", JSONObject.toJSONString(userMoment).getBytes(StandardCharsets.UTF_8));
        //调用工具类 RocketMQUtil，同步发送这条消息出去。
        //（让消息进入 RocketMQ，其他服务可以订阅这个消息，比如推送给好友、刷新首页等等）
        RocketMQUtil.syncSendMsg(producer,msg);
        return BaseResponse.success("创建动态成功");
    }

    @Override
    public BaseResponse getMoments(Long userId) {
        String key="subscribed-"+userId;
        String listStr=redisTemplate.opsForValue().get(key);
        return BaseResponse.success(JSONArray.parseArray(listStr,UserMoment.class));
    }
}
