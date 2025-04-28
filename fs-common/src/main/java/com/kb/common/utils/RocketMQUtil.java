package com.kb.common.utils;

import com.kb.common.exception.InfoException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * @author syg
 * @version 1.0
 */
@Slf4j
public class RocketMQUtil {
    /**
     * 同步发送
     * @param producer
     * @param msg
     */
    public static void syncSendMsg(DefaultMQProducer producer, Message msg){
        try {
            SendResult result = producer.send(msg);
            log.info(result.toString());
        } catch (Exception e) {
            log.error("发送消息失败",e);
            throw new InfoException("发送消息的时候发生了异常",e);
        }
    }

    /**
     * 对消息发送结果不是特别关心,用异步
     * @param producer
     * @param msg
     */
    public static void asyncSendMsg(DefaultMQProducer producer,Message msg,boolean delay) {
        int messageCount=2;
        CountDownLatch2 countDownLatch2 =new CountDownLatch2(messageCount);
        for(int i=0;i<messageCount;i++){
            try {
                if(delay){
                    msg.setDelayTimeLevel(18);
                    log.info("延迟消息准备成功");
                }
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        countDownLatch2.countDown();
                        log.info("{}发送成功",sendResult.getMsgId());
                    }

                    @Override
                    public void onException(Throwable e) {
                        countDownLatch2.countDown();
                        log.error("发送消息的时候发生了异常",e);
                    }
                });
                countDownLatch2.await(5, TimeUnit.SECONDS);
            } catch (Exception e) {
                countDownLatch2.countDown();
                log.error("发送消息的时候发生了异常",e);
                throw new InfoException("发送消息的时候发生了异常",e);
            }

        }
    }


}
