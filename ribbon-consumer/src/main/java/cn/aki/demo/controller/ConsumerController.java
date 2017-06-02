package cn.aki.demo.controller;

import cn.aki.demo.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2017/5/27.
 */
@RestController
public class ConsumerController {

	@Autowired
	private IHelloService helloService;

	@RequestMapping("/hello-consumer")
	public String helloConsumer(){
		return helloService.hello();
	}
}
