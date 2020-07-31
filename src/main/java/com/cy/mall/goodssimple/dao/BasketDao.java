package com.cy.mall.goodssimple.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cy.mall.goodssimple.pojo.Baskets;

import org.apache.ibatis.annotations.Delete;


@Mapper
public interface BasketDao {
	@Select("select *from sku_basket order by id desc")
	List<Baskets> FindAll();
	@Delete("delete from sku_basket where id=#{id}")
	int DeleteById(Integer id);
	int Delete(@Param("ids")Integer...ids);
	
	int Add(Baskets baskets);
	@Update("update sku_basket set name=#{name},model=#{model},skuPrice=#{skuPrice},number=#{number}  where id=#{id}")
	int UpdateById(Baskets baskets);
	
	int Update(Baskets baskets);
	
	int Update2(Baskets baskets);
	
	List<Baskets> doFindByname(String name);
}
