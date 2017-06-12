package cn.aki.demo.controller;

import cn.aki.demo.entity.User;
import cn.aki.demo.service.IHelloService;
import cn.aki.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/27.
 */
@RestController
public class ConsumerController {
	private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);


	@Autowired
	private IHelloService helloService;
	@Autowired
	private IUserService userService;

	@RequestMapping("/hello-consumer")
	public String helloConsumer(){
		return helloService.hello();
	}

	@RequestMapping("/cache/{id}")
	public User cacheGet(@PathVariable Long id){
		User user = helloService.get(id);
		logger.debug(user.getName());
		logger.debug(helloService.get(id).getName());
		helloService.update(user); // 刷新缓存
		logger.debug(helloService.get(id).getName());
		return helloService.get(id);
	}

	@RequestMapping("/collapse/{id}")
	public User collapseGet(@PathVariable Long id){
		return userService.get(id);
	}

}
