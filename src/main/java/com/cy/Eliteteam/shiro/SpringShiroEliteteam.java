package com.cy.Eliteteam.shiro;

import java.util.LinkedHashMap;

import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringShiroEliteteam {
	@Bean
	public RememberMeManager rememberMeManager() {
		RememberMeManager rememberMeManager = new CookieRememberMeManager();
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setMaxAge(7 * 24 * 3600);
		return rememberMeManager;
	}

	@Bean
	public SecurityManager securityManager(Realm realm, RememberMeManager rememberMeManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		securityManager.setRememberMeManager(rememberMeManager);
		return securityManager;

	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);
		sfBean.setLoginUrl("/Eliteteam");
		// 定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		LinkedHashMap<String, String> map = new LinkedHashMap<>();// 能够保证添加顺序
		// 静态资源允许匿名访问:"anon"
//    map.put("/bower_components/**","anon");//anon不能随便写规则
		map.put("/layui/**", "anon");
		map.put("/photo/**", "anon");
		map.put("/Eliteteamzhuce/**", "anon");
		map.put("/PagePrincipal/**", "anon");

		// map.put("/doFindEliteteam/**","anon");
		map.put("/Eliteteamzhu/**", "anon");
		map.put("/doIndex/**", "anon");
		map.put("/doLogin/**", "anon");
		// 除了匿名访问的资源,其它都要认证("authc")后访问
		// map.put("/**","authc");//authc也是规则
		map.put("/**", "user");
		sfBean.setFilterChainDefinitionMap(map);
		return sfBean;
	}
}
