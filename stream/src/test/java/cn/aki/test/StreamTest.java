package cn.aki.test;

import cn.aki.demo.custom.ISinkSender;
import cn.aki.demo.StreamApplication;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/6/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StreamApplication.class)
public class StreamTest {
	@Autowired
	private ISinkSender sender;

	@Autowired @Qualifier(ISinkSender.TOPIC)
	private MessageChannel channel;

	@Test
	public void test(){
		//可使用接口实现类也可以使用MessageChannel
//		sender.output().send(MessageBuilder.withPayload("message from sender").build());
		channel.send(MessageBuilder.withPayload("message from channel").build());
	}

	@After
	public void after(){
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
