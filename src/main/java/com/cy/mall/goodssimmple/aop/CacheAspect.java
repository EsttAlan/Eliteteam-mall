package com.cy.mall.goodssimmple.aop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cy.mall.goodssimmple.catche.DefaultMapCache;

@Component
@Aspect
public class CacheAspect {
	
	  @Autowired
	  private DefaultMapCache saveGetCache;
	  
	  @Pointcut("@annotation(com.cy.mall.annotation.RequiredCache)")
	  public void doCache() {}
	  
	  @Around("doCache()")
	  public Object doAround(ProceedingJoinPoint jp)throws Throwable {
		  //1.获取方法里的参数
		  MethodSignature signature = (MethodSignature)jp.getSignature();
		  Map<String,Object> map=new HashMap<>();
		  String[] parameterNames = signature.getParameterNames();
		  Object[] args = jp.getArgs();
		  for (int i=0;i<parameterNames.length;i++) {
			  map.put(parameterNames[i],args[i]);
		  }
		  //2.根据方法里的参数及参数类型得到对应的key
		  String key=map.toString();
		  System.out.println(key);
		  //3.依据这个key尝试从缓存中获取文件
		  Object rs=saveGetCache.getObject(key);
		  //{keyWords=null, orderOption=null, type=all, pageCurrent=1}
		  //4.若缓存中没有,则执行service中方法,并将结果存入
		  if(rs==null) {
			  rs = jp.proceed();
			  saveGetCache.putObject(key, rs);
		  }
		  return rs;
	  }
}
