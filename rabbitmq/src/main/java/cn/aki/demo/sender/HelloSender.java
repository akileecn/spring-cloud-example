package cn.aki.demo.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/22.
 * 消息生产者
 */
@Component
public class HelloSender {
	private static final Logger logger = LoggerFactory.getLogger(HelloSender.class);

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(){
		String message = "hello";
		logger.info("send:{}", message);
		rabbitTemplate.convertAndSend("rabbit", message);
	}
}
