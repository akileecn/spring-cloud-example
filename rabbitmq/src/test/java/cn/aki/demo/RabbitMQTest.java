package cn.aki.demo;

import cn.aki.demo.sender.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/6/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
public class RabbitMQTest {

	@Autowired
	private HelloSender sender;

	@Test
	public void test(){
		sender.send();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
