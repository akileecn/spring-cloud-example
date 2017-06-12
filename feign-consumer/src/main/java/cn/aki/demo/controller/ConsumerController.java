package cn.aki.demo.controller;

import cn.aki.demo.entity.User;
import cn.aki.demo.client.HelloClient;
import cn.aki.demo.client.RefactorHelloClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/27.
 */
@RestController
@RequestMapping("/feign")
public class ConsumerController {
	private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

	@Autowired
	private HelloClient helloClient; // 使用@FeignClient
	@Autowired
	private RefactorHelloClient refactorHelloClient; // 继承服务端controller和客户端service实现相同接口

	@RequestMapping("/normal")
	public String normal(){
		StringBuilder sb = new StringBuilder();
		sb.append(helloClient.hello()).append("\n");
		sb.append(helloClient.hello(1L, "user")).append("\n");
		User user = new User();
		user.setName("feignUser");
		user.setId(2L);
		sb.append(helloClient.hello(user));
		return sb.toString();
	}

	@RequestMapping("/inherit")
	public String inherit(){
		StringBuilder sb = new StringBuilder();
		sb.append(refactorHelloClient.hello(1L, "user")).append("\n");
		User user = new User();
		user.setName("feignUser");
		user.setId(2L);
		sb.append(refactorHelloClient.hello(user));
		return sb.toString();
	}

}
