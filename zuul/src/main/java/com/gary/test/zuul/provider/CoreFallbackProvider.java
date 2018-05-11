package com.gary.test.zuul.provider;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;

/**
 * core服务挂了之后的Fallback
 * 旧版本的接口是ZuulFallbackProvider
 * @author ztb
 * 2018-05-11 16:16
 */
@Component
public class CoreFallbackProvider implements FallbackProvider {

	/**
	 * 是service名,不是路径
	 * 如果默认fallback给所有用,则return "*"或null
	 * @author ztb
	 * 2018-05-11 16:29
	 * @return
	 */
	@Override
	public String getRoute() {
		return "service-core";
	}

	/**
	 * fallback时的返回内容
	 * @author ztb
	 * 2018-05-11 16:45
	 * @param route
	 * @param cause
	 * @return
	 */
	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return this.getStatusCode().value();
			}

			@Override
			public String getStatusText() throws IOException {
				return this.getStatusCode().getReasonPhrase();
			}

			@Override
			public void close() {
			}

			@Override
			public InputStream getBody() throws IOException {
				String tips = "service-core is unavailable, please try it again later.";
				return new ByteArrayInputStream(tips.getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
				headers.setContentType(mediaType);
				return headers;
			}
		};
	}
}
