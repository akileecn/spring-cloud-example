package cn.aki.demo.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * Created by Administrator on 2017/6/27.
 * 一般消息消费者
 */
//@EnableBinding({Sink.class, ISinkSender.class})
public class SinkReceiver {
	private static final Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

//	@StreamListener(Sink.INPUT)
	@StreamListener(ISinkSender.TOPIC)
	public void receive(Object payLoad){
		// rabbitmq控制台发送的数据 message from rabbitmq
		// 接收的数据 receive:[109, 101, 115, 115, 97, 103, 101, 32, 102, 114, 111, 109, 32, 114, 97, 98, 98, 105, 116, 109, 113]
		logger.info("receive:{}", payLoad);
	}
}
