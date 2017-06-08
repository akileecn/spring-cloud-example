package cn.aki.demo.service.impl;

import cn.aki.demo.entity.User;
import cn.aki.demo.service.IUserService;
import com.google.common.base.Joiner;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */
@Service("userService")
public class UserServiceImpl implements IUserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	// 按请求的顺序分配批处理的结果
	@HystrixCollapser(batchMethod = "getAll", scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL, collapserProperties = {
			// 参考HystrixCollapserProperties
			@HystrixProperty(name = "timerDelayInMilliseconds", value = "5000")
	})
	public User get(Long id) {
		logger.debug("get from single");
//		return restTemplate.getForEntity("http://HELLO-SERVICE:user/" + id, User.class).getBody();
		return null;
	}

	@Override
	@HystrixCommand
	public List<User> getAll(List<Long> ids) {
		logger.debug("get from batch");
		// 返回结果含泛型时使用
		ParameterizedTypeReference<List<User>> typeReference = new ParameterizedTypeReference<List<User>>() {};
		ResponseEntity<List<User>> response = restTemplate.exchange("http://HELLO-SERVICE:users?ids={1}"
				, HttpMethod.GET, HttpEntity.EMPTY, typeReference, Joiner.on(",").join(ids));
		return response.getBody();
	}
}
