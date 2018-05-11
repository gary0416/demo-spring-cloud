package com.gary.test.feign.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author ztb
 * 2018/5/9 22:39
 */
@Service
public class IndexService {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 如果服务不可用,1s连接超时后会进入fallbackMethod,否则等超时
	 * 1.可单独指定,配置key从com.netflix.hystrix.HystrixCommandProperties构造方法里找
	 * @HystrixCommand(fallbackMethod = "getDataFromCoreError", commandProperties =  {
	 * 		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000")
	 * })
	 * 2.或用yml里配置的全局的hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds
	 * @author ztb
	 * 2018-05-11 9:31
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getDataFromCoreError")
	public String getDataFromCore(){
		// 根据service name
		return restTemplate.getForObject("http://SERVICE-CORE/", String.class);
	}

	/**
	 * 调用失败时候走这个
	 * @author ztb
	 * 2018-05-11 8:44
	 * @return
	 */
	public String getDataFromCoreError(){
		return "invoke SERVICE-CORE faild, return default value";
	}

}
