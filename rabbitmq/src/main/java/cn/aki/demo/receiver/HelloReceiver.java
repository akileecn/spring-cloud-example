package cn.aki.demo.receiver;

import cn.aki.demo.sender.HelloSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/22.
 */
@Component
@RabbitListener(queues = "rabbit")
public class HelloReceiver {
	private static final Logger logger = LoggerFactory.getLogger(HelloReceiver.class);

	@RabbitHandler
	public void process(String message){
		logger.info("receive:{}", message);
	}
}
