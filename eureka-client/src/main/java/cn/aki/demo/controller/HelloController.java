package cn.aki.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


/**
 * Created by Administrator on 2017/5/27.
 */
@RestController
public class HelloController {

	private static final Logger log = LoggerFactory.getLogger(HelloController.class);

	@Qualifier("discoveryClient")
	@Autowired
	private DiscoveryClient client;
	@Autowired
	private Registration registration;

	@ResponseBody
	@RequestMapping("/hello")
	public String hello() throws InterruptedException {
		ServiceInstance instance = client.getLocalServiceInstance();
		String instanceId = ((EurekaRegistration)registration).getInstanceConfig().getInstanceId();

		int time = new Random().nextInt(2000);
		Thread.sleep(time);

		log.info("instanceId: {}, sleep:{}", instanceId, time);
		return "hello world " + instance.getPort();
	}
}
