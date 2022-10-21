package com.kuangstudy.rabbitmq.consumer.service.impl;

import com.kuangstudy.rabbitmq.consumer.service.TopicSmsConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = {"sms_queue"})
public class TopicSmsConsumerServiceImpl  implements TopicSmsConsumerService {
    private Logger log = LoggerFactory.getLogger(TopicSmsConsumerServiceImpl.class);

    @Override

    @RabbitHandler
    public void reviceSmsMsg(String msg) {
        log.info("reviceSmsMsg->消息内容为:"+msg);
    }

}
