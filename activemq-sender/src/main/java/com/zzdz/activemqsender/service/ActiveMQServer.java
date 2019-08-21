package com.zzdz.activemqsender.service;


import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @Classname ActiveMQServer
 * @Description TODO
 * @Date 2019/8/20 10:13
 * @Created by joe
 */
@Service
public class ActiveMQServer {


    //默认开启了持久化
    //默认不过期
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue fooQueue;

    @Autowired
    private Topic fooTopic;

    /**
     * 发送队列
     */
    public void sendQueue() {
//        jmsMessagingTemplate.convertAndSend(fooQueue,"foo-queue");

        //增加一些额外的字段
        Map<String, Object> headers = new HashMap<>();
        headers.put("1", "1");
        headers.put("2", "2");
        headers.put("3", 3);
        jmsMessagingTemplate.convertAndSend(fooQueue, "foo-queue", headers);
    }

    /**
     * 1.使用new ActiveMQQueue() 的方式，即使配置文件指定了使用主题的方式，这里任然可以送达队列
     * 2.使用String的方式，会根据配置文件指定的方式使用队列或者主题
     * @param queueName
     * @param msg
     */
    public void sendQueue(String queueName, String msg) {
//        jmsMessagingTemplate.convertAndSend(queueName,msg);
        jmsMessagingTemplate.convertAndSend(new ActiveMQQueue(queueName), msg);
    }


    /**
     * 间隔发送
     */
    @Scheduled(fixedDelay = 3000)
    public void sendQueueScheduled() {
        sendQueue();
    }

    /**
     * 发送主题
     */
    public void sendTopic(String topicName, String msg) {
//        jmsMessagingTemplate.convertAndSend(topicName,msg);
        jmsMessagingTemplate.convertAndSend(new ActiveMQTopic(topicName), msg);
    }

    public void sendTopic() {
        jmsMessagingTemplate.convertAndSend(fooTopic, "foo-topic:" + UUID.randomUUID().toString().substring(0,7));
    }

    @Scheduled(fixedDelay = 3000)
    public void sendTopicScheduled() {
        sendTopic();
    }

}
