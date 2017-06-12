package cn.aki.demo.client;

import cn.aki.demo.entity.User;
import cn.aki.demo.service.IFeignService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/6/12.
 */
@FeignClient(value = "hello-service", fallback = RefactorHelloClient.Fallback.class)
public interface RefactorHelloClient extends IFeignService {
	@Component
	// spring会在类的继承体系(父类和接口)中查询@RequestMapping
	// 需要覆盖@RequestMapping，不然映射会冲突
	@RequestMapping("/error")
	class Fallback implements RefactorHelloClient{
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
