package com.tingfeng.test.service;

import com.tingfeng.test.bean.TestBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TestService {

	private final AtomicInteger num = new AtomicInteger(0);
	
	@Cacheable(cacheNames="test")
	public Integer getNum() {
		return num.incrementAndGet();
	}
	
	@Cacheable(cacheNames="testBean")
	public TestBean testBean() {
		TestBean bean = new TestBean();
		bean.setNum(num.incrementAndGet());
		return bean;
	}
	
	@CacheEvict(cacheNames={"test","testBean"})
	public void evict() {
		
	}
	
	public void reset() {
		num.set(0);
	}
	
}
