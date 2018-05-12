package com.gary.test.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ztb
 * 2018-05-12 16:44
 */
@RestController
public class IndexController {

	@Value("${testKey}")
	private String testKey;

	/**
	 * 从config-server取
	 * @author ztb
	 * 2018-05-12 16:46
	 * @return
	 */
	@GetMapping("/getTestKey")
	public String getTestKey(){
		return "testKey val is " + testKey;
	}

}
