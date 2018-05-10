package com.gary.test.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 定义接口即可,需指定要调用的服务名
 * @author ztb
 * 2018-05-10 22:11
 */
@FeignClient(value = "service-core")
public interface IndexService {

	/**
	 * 访问远程的路径
	 * @author ztb
	 * 2018-05-10 22:13
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String getDataFromCore();

}
