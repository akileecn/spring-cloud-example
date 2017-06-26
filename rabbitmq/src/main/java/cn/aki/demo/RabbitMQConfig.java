package cn.aki.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/6/23.
 */
@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue queue(){
		return new Queue("rabbit");
	}
}
