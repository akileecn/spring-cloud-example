package cn.aki.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Created by Administrator on 2017/6/27.
 */
@SpringBootApplication
public class StreamApplication {
	public static void main(String[] args) {
		SpringApplication.run(StreamApplication.class, args);
	}
}
