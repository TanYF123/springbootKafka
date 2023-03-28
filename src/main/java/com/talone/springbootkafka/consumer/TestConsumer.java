package com.talone.springbootkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    /**
     *  如果不指定容器工厂，那么依然会自动提交
     *  如果不手动committed那么在消费者重启后，将再次消费未提交的数据。
     *  手动提交的位置：
     *      获取数据-提交-业务处理  如果业务处理失败，但该消息已提交，会导致消息丢失，即最多一次消费
     *      获取数据-业务处理-提交  如果提交失败，那么将再次经过一次业务处理，即最少一次消费
     *  只消费一次：
     *      获取redis中的offset，如果存在则代表已消费一次，重新committed.跳过业务处理
     *      如果不存在先经过业务处理，再redis存储offset,再提交committed。当然也可以考虑使用数据库处理。
     *   顺序消费：
     *
     * @param record         记录
     * @param acknowledgment 提交
     */
    @KafkaListener(topics = {"test"},clientIdPrefix = "test",groupId = "test",containerFactory = "kafkaListenerContainerFactory")
    public void consumer(ConsumerRecord<?,?> record, Acknowledgment acknowledgment){
        System.out.println(record.value());
    }
}
