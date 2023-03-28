package com.talone.springbootkafka.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private KafkaTemplate kafkaTemplate;
    @GetMapping("/test/{msg}")
    public String testKafkaProducer(@PathVariable("msg")String msg){
        kafkaTemplate.executeInTransaction(operations -> {
            operations.send("test",msg);
            return msg;
        });
        return "send msg: "+msg;
    }

}
