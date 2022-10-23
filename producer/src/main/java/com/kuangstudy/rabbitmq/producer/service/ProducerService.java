package com.kuangstudy.rabbitmq.producer.service;

public interface ProducerService {
    void makeOrderByTopic(String routeKey) throws InterruptedException;
    void ttlOrderByDirect(String routeKey);
    void getDeadQueue(String routeKey);
}
