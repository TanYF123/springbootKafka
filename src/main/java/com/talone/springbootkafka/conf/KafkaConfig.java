package com.talone.springbootkafka.conf;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaConfig {
//    @Bean
//    public AdminClient adminClient(KafkaAdmin kafkaAdmin){
//        return KafkaAdminClient.create(kafkaAdmin.getConfigurationProperties());
//    }

//    @Bean
//    public NewTopic testTopic(){
//        return TopicBuilder.name("test_topic")
//                .partitions(3)
//                .replicas(1)
//                .build();
//    }


}
