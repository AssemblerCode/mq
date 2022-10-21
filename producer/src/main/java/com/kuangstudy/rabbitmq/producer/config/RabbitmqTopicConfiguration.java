package com.kuangstudy.rabbitmq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration

public class RabbitmqTopicConfiguration {

    @Bean(name = "getTopicExchange")
    public TopicExchange getTopicExchange() {
        return new TopicExchange("topic_order_exchange", true, false);
    }

    public Queue getSmsQueue() {
        Map<String,Object> param=new HashMap<>();
        param.put("x-message-ttl",5000);
        Queue sms_queue = new Queue("email_queue", true,false,false,param);
        return sms_queue;
    }

    @Bean(name = "getEmailQueue")
    public Queue getEmailQueue() {
        Map<String,Object> param=new HashMap<>();
        param.put("x-message-ttl",5000);
        Queue email_queue  = new Queue("email_queue", true,false,false,param);
        return email_queue;
    }

    @Bean(name = "getEmailBinding")
    public Binding getEmailBinding() {
        String routeKey = "";
        return BindingBuilder.bind(getEmailQueue()).to(getTopicExchange()).with(routeKey);
    }

    @Bean(name = "getSmsBinding")
    public Binding getSmsBinding() {
        String routeKey = "";
        return BindingBuilder.bind(getSmsQueue()).to(getTopicExchange()).with(routeKey);
    }

}
