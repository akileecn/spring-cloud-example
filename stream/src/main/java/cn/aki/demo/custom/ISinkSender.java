package cn.aki.demo.custom;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Administrator on 2017/6/27.
 * 自定义消息发送对象
 */
public interface ISinkSender {
	/**
	 * 自定义主题
	 */
	String TOPIC = "custom-topic";
	@Output(TOPIC)
	MessageChannel output();
}
