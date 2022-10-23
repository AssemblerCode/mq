package com.kuangstudy.rabbitmq.producer.service.impl;

import com.kuangstudy.rabbitmq.producer.config.DeadQueueConfiguration;
import com.kuangstudy.rabbitmq.producer.config.RabbitmqTopicConfiguration;
import com.kuangstudy.rabbitmq.producer.config.TtlDirectConfiguration;
import com.kuangstudy.rabbitmq.producer.confirm.MsgConfirmCallBack;
import com.kuangstudy.rabbitmq.producer.confirm.MsgReturnCallBack;
import com.kuangstudy.rabbitmq.producer.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;


@Service
public class ProducerServiceImpl implements ProducerService   {
    @Resource(name = "rabbitmqTopicConfiguration")
    private RabbitmqTopicConfiguration topicConfig;

    @Resource(name = "ttlDirectConfiguration")
    private TtlDirectConfiguration directConfig;

    @Resource(name = "deadQueueConfiguration")
    private DeadQueueConfiguration deadQueueConfig;

    private Logger log = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    private RabbitTemplate rt;

    @Autowired
    MsgReturnCallBack mrcb;

    @Autowired
    MsgConfirmCallBack mccb;

    @Override
    public void makeOrderByTopic(String routeKey) throws InterruptedException {
        Binding emailBinding = topicConfig.getEmailBinding();
        Binding smsBinding = topicConfig.getSmsBinding();

        String exName = topicConfig.getTopicExchange().getName();
        String msg = UUID.randomUUID().toString();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            rt.convertAndSend(exName, routeKey, msg);
            rt.setConfirmCallback(mccb);
            rt.setReturnCallback(mrcb);
            log.info("消息发送成功");
        }
    }

    @Override
    public void ttlOrderByDirect(String routeKey) {
        Binding emailBinding = directConfig.getEmailBinding();
        Binding smsBinding = directConfig.getSmsBinding();
        String exName = directConfig.getDirectExchange().getName();
        String msg = UUID.randomUUID().toString();

        MessagePostProcessor mpp = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message msg) throws AmqpException {
                MessageProperties mp = msg.getMessageProperties();
//                mp.setExpiration("15000");
                mp.setContentEncoding("utf-8");
                return msg;
            }
        };

        rt.convertAndSend(exName, routeKey, msg, mpp);
        log.info("消息发送成功");
    }

    @Override
    public void getDeadQueue(String routeKey) {

    }
}
