package cn.aki.demo.resource;

import cn.aki.demo.entity.User;
import cn.aki.demo.service.IFeignService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/6/12.
 */
@RestController
public class FeignResource implements IFeignService{
	@Override
	public User hello(@RequestParam("id") Long id, @RequestHeader("name") String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}

	@Override
	public String hello(@RequestBody User user) {
		return "hello " + user.getName() + "(" + user.getId() + ")";
	}
}
