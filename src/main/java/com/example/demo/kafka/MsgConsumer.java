package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 * @author xiaojf 2017/3/24 14:36
 */
//@Component
public class MsgConsumer {
    @KafkaListener(topics = {"my-replicated-topic","my-replicated-topic2"})
    public void processMessage(String content) {

        System.out.print("收到消息：");

        System.out.println(content);
    }


}