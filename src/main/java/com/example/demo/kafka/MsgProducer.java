package com.example.demo.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 * @author xiaojf 2017/3/24 14:36
 */
@SuppressWarnings("ALL")
//@Component
public class MsgProducer {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void send() {
        kafkaTemplate.send("my-replicated-topic","xiaojf");
        kafkaTemplate.send("my-replicated-topic2","xiaojf22222222222222222222222");

        kafkaTemplate.metrics();

        kafkaTemplate.execute(new KafkaOperations.ProducerCallback<String, String, Object>() {
            @Override
            public Object doInKafka(Producer<String, String> producer) {
                //这里可以编写kafka原生的api操作
                return null;
            }
        });

        //消息发送的监听器，用于回调返回信息
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
                System.out.println(topic);
            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {
                System.out.println(topic);
            }

            @Override
            public boolean isInterestedInSuccess() {
                return false;
            }
        });
    }
}