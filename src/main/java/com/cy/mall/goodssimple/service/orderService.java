package com.cy.mall.goodssimple.service;

import java.util.List;

import com.cy.mall.goodssimple.pojo.Address;
import com.cy.mall.goodssimple.pojo.order;




public interface orderService {
	List<order> doFindorder();
	
	int dodeletebyid(Integer id);
	
	List<Address> doFindAddess();
	Address doSaveActivity(Address entity);
	int deleteById(Integer id);
	Address findById(Integer id);
}
