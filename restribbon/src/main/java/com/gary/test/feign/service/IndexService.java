package com.gary.test.feign.service;

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

	public String getDataFromCore(){
		// 根据service name
		return restTemplate.getForObject("http://SERVICE-CORE/", String.class);
	}

}
