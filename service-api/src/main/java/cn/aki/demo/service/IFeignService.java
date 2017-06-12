package cn.aki.demo.service;

import cn.aki.demo.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/6/12.
 */
@RequestMapping("/feign")
public interface IFeignService {
	@RequestMapping("/helloUser1")
	// 注解的value值必填
	User hello(@RequestParam("id") Long id, @RequestHeader("name") String name);

	@RequestMapping("/helloUser2")
	String hello(@RequestBody User user);
}
