package cn.aki.demo.filter;

import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/6/19.
 */
public class AccessFilter extends ZuulFilter{

	private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

	@Override
	public String filterType() {
		// pre,route,post,error
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run(){
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		logger.debug("send {} to {}", request.getMethod(), request.getRequestURL().toString());
		if(!Strings.isNullOrEmpty(request.getParameter("error"))){
			logger.debug("error test");
			// 自定义异常消息,SendErrorFilter->ZuulController(ZuulServlet)->DefaultErrorAttributes
			throw new ZuulRuntimeException(new Exception("error test"));
		}
		if(Strings.isNullOrEmpty(request.getParameter("accessToken"))){
			logger.warn("token is empty");
			// 拦截
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		logger.debug("access");
		return null;
	}
}
