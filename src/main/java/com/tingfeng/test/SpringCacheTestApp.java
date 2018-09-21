package com.tingfeng.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关系统配置，如果都通过网关，那么网关系统不拦截后台管理和文件上传系统，只是处理客户端相关系统即可
 */
@SpringBootApplication
@ComponentScan({"com.tingfeng.test","net.oschina.j2cache"})
@EnableCaching
public class SpringCacheTestApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled","false");
		SpringApplication.run(SpringCacheTestApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringCacheTestApp.class);
	}
}
