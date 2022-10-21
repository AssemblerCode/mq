package com.kuangstudy.rabbitmq.producer.service.impl;

import com.kuangstudy.rabbitmq.producer.config.RabbitmqTopicConfiguration;
import com.kuangstudy.rabbitmq.producer.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;


@Service
public class ProducerServiceImpl implements ProducerService {
    @Resource(name = "rabbitmqTopicConfiguration")
    private RabbitmqTopicConfiguration topicConfig;

    private Logger log = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    private RabbitTemplate rt;

    @Override
    public void makeOrderByTopic(String routeKey) {
        Binding emailBinding = topicConfig.getEmailBinding();
        Binding smsBinding = topicConfig.getSmsBinding();

        String exName = topicConfig.getTopicExchange().getName();
        String msg = UUID.randomUUID().toString();
        rt.convertAndSend(exName, routeKey, msg);
        log.info("消息发送成功");
    }
}
