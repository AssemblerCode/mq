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
    public String makeOrderByTopic(String routeKey){
        ps.makeOrderByTopic(routeKey);
        return "code:200";
    }

}
