package cn.aki.demo.controller;

import cn.aki.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/6.
 */
@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User get(@PathVariable Long id){
		User user = new User();
		user.setId(id);
		user.setName(UUID.randomUUID().toString());
		return user;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAll(Long[] ids){
		logger.debug("getAll:{}", ids.toString());
		List<User> list = new ArrayList<>();
		for(Long id : ids){
			list.add(get(id));
		}
//		Collections.reverse(list);
		return list;
	}
}
