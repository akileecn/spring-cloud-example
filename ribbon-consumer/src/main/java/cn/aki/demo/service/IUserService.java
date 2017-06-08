package cn.aki.demo.service;

import cn.aki.demo.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */
public interface IUserService {
	User get(Long id);
	List<User> getAll(List<Long> ids);
}
