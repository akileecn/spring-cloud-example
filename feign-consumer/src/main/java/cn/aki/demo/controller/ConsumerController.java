package cn.aki.demo.controller;

import cn.aki.demo.entity.User;
import cn.aki.demo.service.IHelloService;
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
	private IHelloService helloService;

	@RequestMapping("/hello")
	public String hello(){
		StringBuilder sb = new StringBuilder();
		sb.append(helloService.hello()).append("\n");
		sb.append(helloService.hello(1L, "user")).append("\n");
		User user = new User();
		user.setName("feignUser");
		user.setId(2L);
		sb.append(helloService.hello(user));
		return sb.toString();
	}

}
