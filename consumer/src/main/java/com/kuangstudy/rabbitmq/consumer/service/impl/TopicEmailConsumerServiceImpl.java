package com.kuangstudy.rabbitmq.consumer.service.impl;

import com.kuangstudy.rabbitmq.consumer.service.TopicEmailConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = {"email_queue"})
public class TopicEmailConsumerServiceImpl implements TopicEmailConsumerService  {
    private Logger log = LoggerFactory.getLogger(TopicEmailConsumerServiceImpl.class);

    @Override

    @RabbitHandler
    public void reviceEmailMsg(String msg) {
        log.info("reviceEmailMsg->消息内容为:"+msg);
    }

}
