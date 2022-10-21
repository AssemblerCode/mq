package com.kuangstudy.rabbitmq.producer.service;

public interface ProducerService {
    void makeOrderByTopic(String routeKey);
}
