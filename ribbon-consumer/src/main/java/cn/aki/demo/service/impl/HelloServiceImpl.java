package cn.aki.demo.service.impl;

import cn.aki.demo.service.IHelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2017/6/2.
 */
@Service("helloService")
public class HelloServiceImpl implements IHelloService{
	@Autowired
	private RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "error")
	public String hello() {
		return restTemplate.getForEntity("http://HELLO-SERVICE:hello", String.class).getBody();
	}

	public String error(){
		return "服务器炸了";
	}
}
