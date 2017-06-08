package cn.aki.demo.service;

import cn.aki.demo.entity.User;

/**
 * Created by Administrator on 2017/6/2.
 */
public interface IHelloService {
	String hello();
	User get(Long id);
	void update(User user);
}
