package com.talone.springbootkafka.conf;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

/**
 * kafka消费者配置
 */
@Configuration
public class KafkaConsumerConf {

    /**
     * 使用application.properties中的消费者配置
     * @param kafkaProperties  application.properties中的消费者配置
     * @return 监听容器工厂
     */
    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>> kafkaListenerContainerFactory(KafkaProperties kafkaProperties){
        ConcurrentKafkaListenerContainerFactory containerFactory = new ConcurrentKafkaListenerContainerFactory();
        //使用application.properties中的消费者配置
        containerFactory.setConsumerFactory(new DefaultKafkaConsumerFactory(kafkaProperties.buildConsumerProperties()));
        containerFactory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return containerFactory;
    }
}
