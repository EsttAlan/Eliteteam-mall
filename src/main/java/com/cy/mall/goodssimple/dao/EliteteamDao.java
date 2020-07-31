package com.cy.mall.goodssimple.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.mall.goodssimple.pojo.Eliteteam;




@Mapper
public interface EliteteamDao {
	//Eliteteam findUserByUserName(String username);
int insertEliteteam(Eliteteam entity);
@Select("select*from sys_users where username=#{username}")
Eliteteam findUserByUserName(String username);

	
}
