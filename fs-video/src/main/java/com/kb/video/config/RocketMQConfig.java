package com.kb.video.config;

import com.kb.common.exception.InfoException;
import com.kb.common.utils.RocketMQUtil;
import com.kb.common.utils.TimeUtil;
import com.kb.video.pojo.VideoInfo;
import com.kb.video.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;


/**
 * @author syg
 * @version 1.0
 */
@Configuration
@Slf4j
public class RocketMQConfig {
    @Value("${rocketmq.name.server.address}")
    private String nameSeverAddr;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private VideoService videoService;

    @Resource
    private ApplicationContext applicationContext;


    @Bean("topProducer")
    public DefaultMQProducer topProducer(){
        DefaultMQProducer producer=new DefaultMQProducer("topGroup");
        producer.setNamesrvAddr(nameSeverAddr);
        try {
            producer.start();
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<启动成功{}<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<",nameSeverAddr);
            // 启动时发送第一条消息
            Message msg2=new Message("Topic-top",new byte[1]);
            RocketMQUtil.asyncSendMsg(producer,msg2,true);
        } catch (MQClientException e) {
            log.error("TopGroup消息队列启动失败",e);
            throw new InfoException("RockerMQ:top Group启动失败",e);
        }
        return producer;
    }

    @Bean("topConsumer")
    public DefaultMQPushConsumer topConsumer(){
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("topGroup");
        consumer.setNamesrvAddr(nameSeverAddr);
        // *代表订阅所有
        try {
            consumer.subscribe("Topic-top","*");
            // 监听者,注册并发的监听器,只有一条消息,所以只取第一个
            consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                //获取消息，第一个，list存放的是消费者接收到的消息队列
                //consumeConcurrentlyContext提供了消费上下文，用于记录消费者消费的状态等信息
                MessageExt msg=list.get(0);
                if(msg==null){
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                //通过redis的set操作获取top集合中的所有元素，往往用来存储视频ID
                Set<String> set = redisTemplate.opsForSet().members("top");
                if (set == null) {
                    return null;
                }
                //处理视频ID
                for (String s : set) {
                    //从数据库中查找当前视频的信息
                    VideoInfo videoInfo = videoService.getVideoInfoById(Long.valueOf(s));
                    //更新redis排行榜
                    //先移除当前视频
                    redisTemplate.opsForZSet().remove("topZSet", s);
                    //分类
                    //移除按分类的当前视频
                    redisTemplate.opsForZSet().remove("topZSet" + videoInfo.getCategory(), s);
                    //再次判断是否有该视频，放置视频已经被作者删除
                    if (redisTemplate.hasKey(s)) {
                        //计算分数
                        double score = getScore(videoInfo);
                        redisTemplate.opsForZSet().add("topZSet", s, score);
                        redisTemplate.opsForZSet().add("topZSet" + videoInfo.getCategory(), s, score);
                    } else {
                        redisTemplate.opsForSet().remove("top", s);
                    }
                }
                Message msg2=new Message("Topic-top",new byte[1]);
                DefaultMQProducer producer=(DefaultMQProducer)applicationContext.getBean("topProducer");
                //再次发送消息
                RocketMQUtil.asyncSendMsg(producer,msg2,true);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        } catch (MQClientException e) {
            log.error("接收top消息失败,消费者出现异常",e);
            throw new InfoException("接收top消息失败,消费者出现异常",e);
        }
        return consumer;
    }

    private double getScore(VideoInfo videoInfo) {
        return videoInfo.getBarrages()*10+(Long) redisTemplate.opsForHash().get("like",videoInfo.getId())*5+
                redisTemplate.opsForHyperLogLog().size("hl" + videoInfo.getId())*20+
                (71-TimeUtil.between(TimeUtil.getDateTime(videoInfo.getCreateTime()),
                        TimeUtil.getDateTime(new Date()), ChronoUnit.HOURS))*15;
    }
}
