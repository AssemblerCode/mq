package com.kuangstudy.rabbitmq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration

public class DeadQueueConfiguration {

    @Bean(name = "getDeadExchange")
    public DirectExchange getDeadExchange() {
        return new DirectExchange("dead_direct_exchange", true, false);
    }

    @Bean(name = "getDeadQueue")
    public Queue getDeadQueue() {
        Map<String,Object> param=new HashMap<>();
        param.put("x-message-ttl",10000);
        param.put("x-dead-letter-exchange", "dead_direct_exchange");
        param.put("x-dead-letter-routing-key", "dead");
        Queue q = new Queue("dead_queue", true,false,false,param);
        return  q;
    }

    @Bean(name = "getDeadBinding")
    public Binding getDeadBinding() {
        String routeKey = "";
        return BindingBuilder.bind(getDeadQueue()).to(getDeadExchange()).with(routeKey);
    }

}
