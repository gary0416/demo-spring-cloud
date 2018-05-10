package com.gary.test.feign.controller;

import com.gary.test.feign.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ztb
 * 2018/5/9 22:39
 */
@RestController
public class IndexController {

	@Value("${spring.application.name}")
	private String serviceName;
	@Value("${server.port}")
	private String servicePort;

	@RequestMapping("/")
	public String index() {
		return "service " + serviceName + " on port " + servicePort;
	}

	@Autowired
	private IndexService indexService;

	/**
	 * 依旧支持负载均衡
	 * @author ztb
	 * 2018-05-10 22:16
	 * @return
	 */
	@RequestMapping("/getDataFromCore")
	public String getDataFromCore() {
		return indexService.getDataFromCore();
	}

}
