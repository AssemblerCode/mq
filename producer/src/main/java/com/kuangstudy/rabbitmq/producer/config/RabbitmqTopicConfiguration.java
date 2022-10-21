package com.kuangstudy.rabbitmq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class RabbitmqTopicConfiguration {

    @Bean(name = "getTopicExchange")
    public TopicExchange getTopicExchange() {
        return new TopicExchange("topic_order_exchange", true, false);
    }

    @Bean(name = "getSmsQueue")
    public Queue getSmsQueue() {
        return new Queue("sms_queue", true);
    }

    @Bean(name = "getEmailQueue")
    public Queue getEmailQueue() {
        return new Queue("email_queue", true);
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
