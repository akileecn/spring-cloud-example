package cn.aki.demo.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/6.
 * 初始化HystrixRequestContext
 * 使用到HystrixRequestCache HystrixRequestLog HystrixCollapser时需要配置
 */
public class MyHystrixFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.info("initialize HystrixRequestContext");
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			chain.doFilter(request, response);
		} finally {
			context.shutdown();
		}
	}
}
