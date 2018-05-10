package com.gary.test.eurekaserver.security;

import com.gary.test.eurekaserver.security.password.Md5PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置
 * @author ztb
 * 2018-04-13 0:59
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * 必须指定PasswordEncoder
	 * @author ztb
	 * 2018-05-10 20:41
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 推荐: BCryptPasswordEncoder,PasswordEncoderFactories,MessageDigestPasswordEncoder("MD5")
		// 备选: Pbkdf2PasswordEncoder,SCryptPasswordEncoder,DelegatingPasswordEncoder
		return new Md5PasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("gary")
				// 123456
				.password("e10adc3949ba59abbe56e057f20f883e")
				.roles("ADMIN");
	}

	/**
	 * allows configuration of web based security at a resource level,
	 * based on a selection match - e.g.
	 * @author ztb
	 * 2018-04-25 22:02
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
				.csrf()
					.disable()
				.authorizeRequests()
					.antMatchers(new String[]{
							"/**/favicon.ico",
							"/webjars/**",
							"/scripts/**",
							"/styles/**",
							"/images/**",
							"/upload/**"
					}).permitAll()
					.anyRequest()
						.authenticated()
				.and()
					.sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
					.httpBasic();
		// @formatter:on
	}

	/**
	 * is used for configuration settings that impact global security
	 * (ignore resources, set debug mode,
	 * reject requests by implementing a custom firewall definition).
	 * For example,
	 * the following method would cause any request that
	 * starts with /resources/ to be ignored for authentication purposes.
	 * @author ztb
	 * 2018-04-25 20:27
	 * @param web
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(false);
		web.ignoring()
				.antMatchers("/actuator/**");
	}

}