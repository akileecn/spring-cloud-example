package cn.aki.demo.conctroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/6/19.
 * 本地跳转
 */
@RestController
@RequestMapping("/local")
public class LocalController {
	@RequestMapping("/hello")
	public String forward(){
		return "local hello";
	}
}
