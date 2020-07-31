package com.cy.mall.goodssimple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.mall.goodssimple.dao.orderDao;
import com.cy.mall.goodssimple.pojo.Address;
import com.cy.mall.goodssimple.pojo.order;
import com.cy.mall.goodssimple.service.orderService;


@Service
public class orderSerciceImpl implements orderService {
	@Autowired
	private orderDao od;

	@Override
	public List<order> doFindorder() {
		
		return od.doFindorder();
	}

	@Override
	public int dodeletebyid(Integer id) {
	
		return od.dodeletebyid(id);
	}
	
	@Override
	public List<Address> doFindAddess() {
		
		return od.doFindAddess();
	}

	@Override
	public Address doSaveActivity(Address entity) {
		od.insertActivity(entity);
		
			return entity;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return od.deleteById(id);
	}

	@Override
	public Address findById(Integer id) {
		// TODO Auto-generated method stub
		return od.findById(id);
	}


}
