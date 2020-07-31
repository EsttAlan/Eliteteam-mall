package com.cy.mall.goodssimple.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.mall.common.vo.JsonResult;
import com.cy.mall.goodssimple.pojo.Eliteteam;
import com.cy.mall.goodssimple.service.impl.EliteteamImpl;


@Controller
//@RequestMapping("/Eliteteam/")
public class EliteteamController {
	@Autowired
	private EliteteamImpl eliteteamImpl;

	@RequestMapping("doIndex")

	public String Eliteteam(Eliteteam entity) {
		System.out.println(entity);
		eliteteamImpl.insertEliteteam(entity);
		return "forward:Eliteteamzhuce";

	}
	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username,String password,boolean isRememberMe) {
		UsernamePasswordToken token=new UsernamePasswordToken(username,password);
		//设置记住功能
		token.setRememberMe(isRememberMe);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.login(token);
		return new JsonResult("login ok");
	}

	@RequestMapping("Eliteteam")
	public String eliteTeam() {
		return "EliteteamUser";

	}
	@RequestMapping("/Eliteteamzhuce")
	public String zhuce1() {
		return "ELiteteamzhuce.html";

	}
	@RequestMapping("/Eliteteamzhu")
	public String zhuce() {
		return "zhuce";

	}

	@RequestMapping("/test")
	public String testdemo() {
		return "index3";

	}
}