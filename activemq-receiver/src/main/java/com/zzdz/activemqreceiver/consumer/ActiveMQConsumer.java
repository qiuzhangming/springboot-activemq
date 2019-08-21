package com.zzdz.activemqreceiver.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @Classname ActiveMQConsumer
 * @Description TODO
 * @Date 2019/8/20 10:21
 * @Created by joe
 */
@Slf4j
@Component
public class ActiveMQConsumer {

    /**
     * 接收队列
     * 1、队列模式，若两个JmsListener同时监听一个队列，两个Listener都会自动负载均衡
     * @param textMessage
     * @throws JMSException
     */
    @JmsListener(destination = "${zzdz.queue.foo}")
    public void receive(TextMessage textMessage) throws JMSException {
//        System.out.println(textMessage);
//        System.out.println(textMessage.getStringProperty("1"));
//        System.out.println(textMessage.getIntProperty("2"));
//        System.out.println(textMessage.getIntProperty("3"));
        log.info(textMessage.getText());
    }


    @JmsListener(destination = "count-queue")
    public void receive1(TextMessage textMessage) throws JMSException {
        //System.out.println(textMessage);
        log.info("count-queue-1:"+textMessage.getText());
        //throw new RuntimeException(); //6次失败后会进入死信队列
    }


    @JmsListener(destination = "count-queue")
    public void receive2(TextMessage textMessage) throws JMSException {
        //System.out.println(textMessage);
        log.info("count-queue-2:"+textMessage.getText());
    }

    /**
     * 接收主题
     * 1.主题模式下，若两个JmsListener同时监听一个主题，两个Listener都会收到相同的消息
     * @param textMessage
     * @throws JMSException
     */
    @JmsListener(destination = "${zzdz.topic.foo}")
    public void topicReceive(TextMessage textMessage) throws JMSException {
        textMessage.getJMSMessageID();
        //System.out.println(textMessage);
        log.info(textMessage.getText());
    }

//    @JmsListener(destination = "${zzdz.topic.foo}")
//    public void topicReceive0(String str) {
//        log.info("another:"+str);
//    }


    /**
     * 持久化订阅
     * @param textMessage
     * @throws JMSException
     */
    @JmsListener(destination = "count-topic", containerFactory = "jmsTopicListener")
    public void topicReceive1(TextMessage textMessage) throws JMSException {
        //System.out.println(textMessage);
        log.info("count-topic-1:"+textMessage.getText());
    }

    /**
     * 非持久化订阅
     * @param textMessage
     * @throws JMSException
     */
    @JmsListener(destination = "count-topic")
    public void topicReceive2(TextMessage textMessage) throws JMSException {
        //System.out.println(textMessage);
        log.info("count-topic-2:"+textMessage.getText());
    }

}
