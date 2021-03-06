package cn.aki.demo.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;

import java.util.UUID;

/**
 * Created by Administrator on 2017/6/28.
 */
@EnableBinding(Processor.class)
public class App2 {
	private static final Logger logger = LoggerFactory.getLogger(App2.class);

	// output destination: input
	@InboundChannelAdapter(value = Processor.OUTPUT, poller = @Poller(fixedDelay = "2000"))
	public String createMessage(){
		return UUID.randomUUID().toString();
	}

	// input destination: output
	@StreamListener(Processor.INPUT)
	public void receive(Object payLoad){
		logger.info("App2 receive:{}", payLoad);
	}
}
