package cn.aki.demo.resource;

import cn.aki.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


/**
 * Created by Administrator on 2017/5/27.
 */
@RestController
public class HelloResource {

	private static final Logger log = LoggerFactory.getLogger(HelloResource.class);

	@Qualifier("discoveryClient")
	@Autowired
	private DiscoveryClient client;
	@Autowired
	private Registration registration;

	@ResponseBody
	@RequestMapping("/hello")
	public String hello() throws InterruptedException {
		ServiceInstance instance = client.getLocalServiceInstance();
		String instanceId = ((EurekaRegistration) registration).getInstanceConfig().getInstanceId();

		int time = new Random().nextInt(2000);
		Thread.sleep(time);

		log.info("instanceId: {}, sleep:{}", instanceId, time);
		return "hello world " + instance.getPort();
	}


	@RequestMapping("/helloUser1")
	public User hello(@RequestParam Long id, @RequestHeader String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}

	@RequestMapping("/helloUser2")
	public String hello(@RequestBody User user) {
		return "hello " + user.getName() + "(" + user.getId() + ")";
	}
}
