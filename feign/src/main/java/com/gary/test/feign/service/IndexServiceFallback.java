package com.gary.test.feign.service;

import org.springframework.stereotype.Service;

/**
 * fallback时候使用
 * @author ztb
 * 2018-05-11 12:08
 */
@Service
public class IndexServiceFallback implements IndexService{

	@Override
	public String getDataFromCore() {
		return "invoke SERVICE-CORE faild, return default value";
	}

}
