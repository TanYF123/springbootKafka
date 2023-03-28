package com.talone.springbootkafka.conf;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 *  @EnableConfigurationProperties(TopicConfiguration.class)
 *  作用：启动配置类，即将配置类交给spring ioc容器管理。如果 TopicConfiguration 未添加注入容器的注解，例如：@service、@component、@configuration
 *  那么你就需要在该类添加此注解。
 */
@Configuration
public class TopicAdmin {

    private final TopicConfiguration topicConfiguration;

    private final GenericApplicationContext context;

    public TopicAdmin(TopicConfiguration topicConfiguration, GenericApplicationContext context){
        this.topicConfiguration = topicConfiguration;
        this.context = context;
    }

    /**
     * 项目启动时初始化topic
     */
    @PostConstruct
    public void init(){
        initialization(topicConfiguration.getTopics());
    }

    public void initialization(List<TopicConfiguration.Topic> topics){
        topics.forEach(t->{
            context.registerBean(t.getName(), NewTopic.class,t::newTopic);
        });
    }
}
