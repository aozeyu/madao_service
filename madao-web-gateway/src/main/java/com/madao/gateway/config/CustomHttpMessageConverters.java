package com.madao.gateway.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
public class CustomHttpMessageConverters {

	/**
	 * fix feign.codec.DecodeException:
	 * No qualifying bean of type
	 * 'org.springframework.boot.autoconfigure.http.HttpMessageConverters'
	 * available: expected at least 1 bean which qualifies as autowire candidate.
	 * Dependency annotations:
	 * {@org.springframework.beans.factory.annotation.Autowired(required=true)}
	 */
	@Bean
	public HttpMessageConverters customConverters() {
		return new HttpMessageConverters();
	}
}
