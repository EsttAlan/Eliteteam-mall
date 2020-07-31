package com.cy.mall.goodssimple.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.mall.goodssimple.pojo.GoodsSimplePojo;

@Mapper
public interface GoodsSimpleDao {
	//1.使用pageHelper插件进行分页查询
	List<GoodsSimplePojo> ListGoodsSimple(String keyWords,String type);
}
