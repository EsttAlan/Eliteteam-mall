package com.cy.mall.goodssimmple.catche;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class DefaultMapCache {
	//使用线程安全的ConcurrentHashMap对象作为存储数据的容器
	private Map<Object,Object> cache=new ConcurrentHashMap<>();
	private long startime=System.currentTimeMillis();
	public void putObject(Object key,Object value) {
		cache.put(key, value);
	}
	public Object getObject(Object key) {
		//取数据前先清空之前半小时的记录
		clearObjectj();
		Object obj = cache.get(key);
		return obj;
	}
	public void clearObjectj() {
		long t=System.currentTimeMillis();
		if((t-startime)>=1000*60*30) {
			System.out.println("前半小时的缓存已被清空");
			cache.clear();
			//更新开始存放缓存的时间
			startime=System.currentTimeMillis();
		}
	}
}
