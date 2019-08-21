package com.zzdz.activemqsender.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @Classname ActiveMQConfig
 * @Description TODO
 * @Date 2019/8/20 10:10
 * @Created by joe
 */
@Configuration
public class ActiveMQConfig {

    @Value("${zzdz.queue.foo}")
    private String queueName;

    @Value("${zzdz.topic.foo}")
    private String topicName;

    /**
     * 配置队列
     * @return
     */
    @Bean
    public Queue fooQueue() {
        return new ActiveMQQueue(queueName);
    }

    /**
     * 配置主题
     * @return
     */
    @Bean
    public Topic fooTopic() {
        return new ActiveMQTopic(topicName);
    }

}
