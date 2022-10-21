package com.kuangstudy.rabbitmq.producer.controller;


import com.kuangstudy.rabbitmq.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    ProducerService ps;

    @PostMapping("/makeOrderByTopic")
    public String makeOrderByTopic(String routeKey) throws InterruptedException {
        ps.makeOrderByTopic(routeKey);
        return "code:200";
    }

    @PostMapping("/ttlOrderByDirect")
    public String ttlOrderByDirect(String routeKey) throws InterruptedException {
        ps.ttlOrderByDirect(routeKey);
        return "code:200";
    }

    @PostMapping("/getDeadQueue")
    public String getDeadQueue(String routeKey) throws InterruptedException {
        ps.getDeadQueue(routeKey);
        return "code:200";
    }

}
