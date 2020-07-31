package com.cy.mall.goodssimple.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.mall.goodssimple.pojo.Baskets;


public interface basketService {
	List<Baskets> FindAll();
	int Delete(Integer...ids);
	int Add(Baskets baskets);
//	int UpdateById(Baskets baskets);
	int DeleteById(Integer id);
	List<Baskets> doFindByname(String name);
	int Update(Baskets baskets);
	int Update2(Baskets baskets);
}
