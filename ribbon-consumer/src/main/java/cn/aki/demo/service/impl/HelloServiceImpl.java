package cn.aki.demo.service.impl;

import cn.aki.demo.entity.User;
import cn.aki.demo.service.IHelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Created by Administrator on 2017/6/2.
 */
@Service("helloService")
public class HelloServiceImpl implements IHelloService{
	private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
	@Autowired
	private RestTemplate restTemplate;

	@Override
	// 线程池划分默认根据groupKey,groupKey默认为类名,commandKey默认为方法名
	@HystrixCommand(fallbackMethod = "error", threadPoolKey = "HelloService.hello")
	public String hello() {
		logger.info("call HELLO-SERVICE:hello");
		return restTemplate.getForEntity("http://HELLO-SERVICE:hello", String.class).getBody();
	}

	@Override
	@CacheResult // 线程缓存仅在一次request请求中有效
	@HystrixCommand
	public User get(Long id) {
		User user = new User();
		user.setId(id);
		user.setName(UUID.randomUUID().toString());
		return user;
	}

	@Override
	@CacheRemove(commandKey = "get")
	@HystrixCommand
	public void update(@CacheKey("id") User user) {

	}

	public String error(Throwable e){
		return "服务器炸了:" + e.getMessage();
	}

}
