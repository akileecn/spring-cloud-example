package cn.aki.demo.transform;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/27.
 */
//@EnableBinding(Sink.class)
public class Sender {
	private static final Logger logger = LoggerFactory.getLogger(Sender.class);

	@InboundChannelAdapter(value = Sink.INPUT, poller = @Poller(fixedDelay = "2000"))
	public String createMessage(){
		User user = new User();
		user.setName(UUID.randomUUID().toString());
		user.setAge(new Random().nextInt(100));
		String message = JSON.toJSONString(user);
		logger.info("send message:{}",message);
		return message;
	}

	@StreamListener(Sink.INPUT)
	public void receive(User user){
		logger.info("receive message:{}", user);
	}
}
