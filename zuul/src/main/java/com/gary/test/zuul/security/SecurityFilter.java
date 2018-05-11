package com.gary.test.zuul.security;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 安全过滤
 * @author ztb
 * 2018-05-11 15:21
 */
@Component
public class SecurityFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 过滤器类型
	 * pre: 请求被路由之前调用。可利用其实现身份验证等
	 * routing: 请求路由到微服务，用于构建发送给微服务的请求，并使用Apache Http Client或者Netflix Ribbon请求微服务
	 * post: 路由到微服务以后执行，比如为响应添加标准的HTTP Header，收集统计信息和指标，将响应从微服务发送到客户端等
	 * error: 在其他阶段发生错误时执行该过滤器
	 * custom: 自定义
	 * @author ztb
	 * 2018-05-11 17:14
	 * @return
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 优先级,数字大的靠后
	 * @author ztb
	 * 2018-05-11 17:13
	 * @return
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 是否需要过滤,可以加例外等操作
	 * @author ztb
	 * 2018-05-11 17:14
	 * @return
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		logger.debug("visit url:" + request.getRequestURI());
		String accessToken = request.getParameter("access_token");
		if (accessToken == null) {
			logger.debug("access forbidden");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			try {
				ctx.getResponse().getWriter().write("access forbidden");
			} catch (Exception e) {
			}
		} else {
			logger.debug("access allowed");
		}
		return null;
	}

}
