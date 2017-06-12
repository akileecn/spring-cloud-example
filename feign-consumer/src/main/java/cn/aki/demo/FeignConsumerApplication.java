package cn.aki.demo;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Created by Administrator on 2017/6/9.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(FeignConsumerApplication.class, args);
	}

	// 设置feign日志级别
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL; // 默认为NONE
	}
}
