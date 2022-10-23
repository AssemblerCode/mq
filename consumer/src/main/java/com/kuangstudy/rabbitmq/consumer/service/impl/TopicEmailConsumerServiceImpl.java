package com.kuangstudy.rabbitmq.consumer.service.impl;

import com.kuangstudy.rabbitmq.consumer.service.TopicEmailConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel ;

import java.io.IOException;

@Service
@RabbitListener(queues = {"email_queue"})
public class TopicEmailConsumerServiceImpl implements TopicEmailConsumerService  {
    private Logger log = LoggerFactory.getLogger(TopicEmailConsumerServiceImpl.class);

    @Override
    @RabbitHandler
    public void reviceEmailMsg(String msg) {
        log.info("reviceEmailMsg->消息内容为:"+msg);
    }

    @Override

    @RabbitHandler
    public void reviceEmailMsg(String str, Channel c, Message msg) throws IOException {
        log.info("reviceEmailMsg->消息内容为:"+msg);

        try {
            int i = 1 / 0;
            c.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            if(msg.getMessageProperties().getRedelivered()){
                log.info("消息已经被处理过了,拒绝接收");
                c.basicReject(msg.getMessageProperties().getDeliveryTag(),false);
            }else {
                c.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
                log.error("消息即将再次返回队列处理...");
                c.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

}
