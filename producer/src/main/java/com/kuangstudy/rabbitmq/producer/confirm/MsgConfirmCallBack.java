package com.kuangstudy.rabbitmq.producer.confirm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class MsgConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    private static final Logger log = LoggerFactory.getLogger(MsgConfirmCallBack.class);

    @Override
    public void confirm(CorrelationData d, boolean b, String s) {
        if (b) {
            log.info("消息成功投送到交换机");
        } else {
            log.info("消息未能投送到交换机");
        }
    }
}
