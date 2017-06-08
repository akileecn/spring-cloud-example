package cn.aki.demo;

import cn.aki.demo.filter.MyHystrixFilter;
import com.google.common.collect.Lists;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2017/5/27.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker // 开启断路器
public class ConsumerApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new MyHystrixFilter());
		registrationBean.setUrlPatterns(Lists.newArrayList("/cache/*", "/collapse/*"));
		return registrationBean;
	}

}
