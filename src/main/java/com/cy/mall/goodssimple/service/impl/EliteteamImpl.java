package com.cy.mall.goodssimple.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.mall.goodssimple.dao.EliteteamDao;
import com.cy.mall.goodssimple.pojo.Eliteteam;
import com.cy.mall.goodssimple.service.EliteteamService;

@Service
public  class EliteteamImpl implements EliteteamService {
@Autowired
private EliteteamDao eliteteamDao;
	@Override
	public int insertEliteteam(Eliteteam entity) {
//		String password = entity.getPassword();
//		String conpwd = entity.getSalt();
//		String username = entity.getUsername();
//		//1.校验
//		//0)传入对象不能为空
//		if(entity==null){
//			throw new IllegalArgumentException("输入异常");
//		}
//		//1)密码不能为空
//		
//		//2)用户名不能为空
//		if(StringUtils.isEmpty(username)) {
//			throw new IllegalArgumentException("用户名不能为空");
//		}
//		if(StringUtils.isEmpty(password)) {
//			throw new IllegalArgumentException("密码不能为空");
//		}
//		//3)两次密码要一致
//		if(password.equals(username)) {
//			throw new IllegalArgumentException("两次密码不一致");
//		}
//		//2.密码加密
//		String salt = UUID.randomUUID().toString();
//		ByteSource salt1 = ByteSource.Util.bytes(salt);
//		ByteSource pwdb = ByteSource.Util.bytes(password);
//		String pwd = (new SimpleHash("MD5", pwdb, salt1, 1)).toHex();
//		
		 int rows = eliteteamDao.insertEliteteam(entity);
		
		return rows;
	}

}
