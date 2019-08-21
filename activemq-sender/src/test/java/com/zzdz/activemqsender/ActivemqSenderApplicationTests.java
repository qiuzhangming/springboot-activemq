package com.zzdz.activemqsender;

import com.zzdz.activemqsender.service.ActiveMQServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqSenderApplicationTests {

    @Autowired
    ActiveMQServer activeMQServer;

    @Test
    public void contextLoads() {
        activeMQServer.sendQueue();
    }

    @Test
    public void sendQueue() {
        for (int i = 0; i < 3; i++) {
            activeMQServer.sendQueue("count-queue", "queue:"+i);
        }
    }

    @Test
    public void sendTopic() {
        for (int i = 0; i < 3; i++) {
            activeMQServer.sendTopic("count-topic","topic"+i);
        }
    }
}
