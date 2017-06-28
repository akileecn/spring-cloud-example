package cn.aki.demo.response;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Administrator on 2017/6/28.
 */
public interface ICustomProcessor{
	@Input(Source.OUTPUT)
	SubscribableChannel input();

	@Output(Sink.INPUT)
	MessageChannel output();
}
