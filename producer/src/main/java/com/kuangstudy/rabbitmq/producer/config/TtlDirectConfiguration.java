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

public class TtlDirectConfiguration {

    @Bean(name = "TtlDirectConfiguration_getTopicExchange")
    public DirectExchange getDirectExchange() {
        return new DirectExchange("ttl_direct_order_exchange", true, false);
    }

    private Map<String, Object> getParam() {
        Map<String, Object> param = new HashMap<>();
        param.put("x-message-ttl", 5000);
        param.put("x-max-length", 5);
        param.put("x-dead-letter-exchange", "dead_direct_exchange");
        param.put("x-dead-letter-routing-key", "dead");
        return param;
    }

    @Bean(name = "TtlDirectConfiguration_getSmsQueue")
    public Queue getSmsQueue() {
        Map<String, Object> param = getParam();
        Queue sms_queue = new Queue("ttl_sms_queue", true, false, false, param);
        return sms_queue;
    }

    @Bean(name = "TtlDirectConfiguration_getEmailQueue")
    public Queue getEmailQueue() {
        Map<String, Object> param = getParam();
        Queue email_queue = new Queue("ttl_email_queue", true, false, false, param);
        return email_queue;
    }

    @Bean(name = "TtlDirectConfiguration_getEmailBinding")
    public Binding getEmailBinding() {
        String routeKey = "ttl";
        return BindingBuilder.bind(getEmailQueue()).to(getDirectExchange()).with(routeKey);
    }

    @Bean(name = "TtlDirectConfiguration_getSmsBinding")
    public Binding getSmsBinding() {
        String routeKey = "ttl";
        return BindingBuilder.bind(getSmsQueue()).to(getDirectExchange()).with(routeKey);
    }

}
