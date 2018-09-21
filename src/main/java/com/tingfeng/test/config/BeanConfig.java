package com.tingfeng.test.config;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义的bean
 */
@Configuration
public class BeanConfig {

    /*@Bean
    public CacheChannel  cacheChannel(){
        CacheChannel cache = J2Cache.getChannel(); //获取 J2Cache 操作接口
        return cache;
    }*/
}
