package com.kuangstudy.rabbitmq.producer.confirm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MsgReturnCallBack implements RabbitTemplate.ReturnCallback{

    private static final Logger log = LoggerFactory.getLogger(MsgReturnCallBack.class);

    @Override
    public void returnedMessage(Message msg, int replyCode, String replyTxt, String exName, String routeKey) {
        log.info("消息未能投送到队列.交换机名称:"+exName+",routeKey:"+routeKey+",返回错误code:"+replyCode+",返回错误内容:"+replyTxt+",消息内容:"+new String(msg.getBody()));
    }
}
