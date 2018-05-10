package com.gary.test.eurekaserver.security.password;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * md5加密,不加盐,for spring security
 * PasswordEncoderFactories里有加盐的
 * @author ztb
 * 2018-04-13 0:45
 */
public class Md5PasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return MD5Utils.getEncryptedPassword((String) rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String encodedRawPassword = MD5Utils.getEncryptedPassword((String)rawPassword);
		return encodedPassword.equalsIgnoreCase(encodedRawPassword);
	}

}
