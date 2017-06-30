package cn.aki.demo.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * Created by Administrator on 2017/6/28.
 */
//@EnableBinding(Processor.class)
public class App1 {
	private static final Logger logger = LoggerFactory.getLogger(App1.class);

	@Value("${server.port}")
	private String port;

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	// @ServiceActivator = @StreamListener + @SendTo
//	@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public String receive(Object payLoad) {
		logger.info("App1 receive:{}", payLoad);
		return "return from app1(" + port + ")" + payLoad;
	}
}
