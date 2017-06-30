package cn.aki.demo.custom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Administrator on 2017/6/27.
 * 自定义消息发送对象
 */
public interface ISinkSender {
	/**
	 * 自定义channel,destination默认值与channel相同
	 */
	String INPUT = "custom-input";
	String OUTPUT = "custom-output";
	@Output(OUTPUT)
	MessageChannel output();

	@Input(INPUT)
	SubscribableChannel input();
}
