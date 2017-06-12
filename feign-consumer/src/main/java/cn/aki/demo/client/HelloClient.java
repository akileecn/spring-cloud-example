package cn.aki.demo.client;

import cn.aki.demo.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/6/9.
 */
@FeignClient(value = "hello-service", fallback = HelloClient.Fallback.class)
public interface HelloClient {

	@RequestMapping("/hello")
	String hello();

	@RequestMapping("/helloUser1")
	// 注解的value值必填
	User hello(@RequestParam("id") Long id, @RequestHeader("name") String name);

	@RequestMapping("/helloUser2")
	String hello(@RequestBody User user);

	/**
	 * 服务降级，需要开启Hystrix
	 */
	@Component
	class Fallback implements HelloClient{

		@Override
		public String hello() {
			return "error";
		}

		@Override
		public User hello(Long id, String name) {
			User user = new User();
			user.setId(-1L);
			user.setName("error");
			return user;
		}

		@Override
		public String hello(User user) {
			return "error";
		}
	}
}
