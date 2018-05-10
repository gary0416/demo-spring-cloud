package com.gary.test.servicecore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ztb
 * 2018/5/9 17:15
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

}
