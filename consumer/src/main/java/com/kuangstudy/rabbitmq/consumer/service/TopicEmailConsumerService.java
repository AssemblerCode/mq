package com.kuangstudy.rabbitmq.consumer.service;

import org.springframework.amqp.core.Message;

import com.rabbitmq.client.Channel ;

import java.io.IOException;

public interface TopicEmailConsumerService {
    void reviceEmailMsg(String msg);
    void reviceEmailMsg(String str, Channel c, Message msg) throws IOException;

}
