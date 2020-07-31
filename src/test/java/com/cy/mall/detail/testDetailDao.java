package com.cy.mall.detail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.mall.detail.bo.Option;
import com.cy.mall.detail.bo.Property;
import com.cy.mall.detail.dao.DetailDao;
import com.cy.mall.detail.entity.Sku;
import com.cy.mall.detail.entity.SkuOption;

@SpringBootTest
public class testDetailDao {
	
	@Autowired
	private DetailDao detailDao;
	
	
	@Test
	public void findOptionNameById() {
		List<Integer> optionIds = new ArrayList<>();
		System.out.println(detailDao);
		optionIds.add(3);
		optionIds.add(4);
		List<String> res = detailDao.findOptionNameById(optionIds);
		for (String name : res) {
			System.out.println(name);
		}
	}
	
	@Test
	public void isExisted() {
		Integer res = detailDao.isExisted(12);
	
		System.out.println(res);
	}
	
	@Test
	public void findReserveBySkuId() {
		Integer res = detailDao.findReserveBySkuId(12);
		System.out.println("库存:"+res);
	}
	
	
	@Test
	public void findAllOptionsBySkuId() {
		List<SkuOption> findAllOptionsBySkuId = detailDao.findAllOptionsBySkuId(1);
		for (SkuOption option : findAllOptionsBySkuId) {
			System.out.println(option);
		}
	}
	
	@Test
	public void findSkuBySkuId() {
		Sku sku = detailDao.findSkuBySkuId(1);	
		System.out.println(sku.getImg());
		
	}
	
	@Test
	public void findOptionsByPropertyIdTests() {
		List<Option> list = detailDao.findOptionsByPropertyId(1);
		for (Option option : list) {
			System.out.println(option);
		}
	}
	
	@Test
	public void findPropertyIdBySkuId() {
		List<Integer> list = detailDao.findPropertyIdBySkuId(1);
		for (Integer i : list) {
			System.out.println(i);
		}
	}
	
	@Test
	public void findPropertyById() {
		Property r = detailDao.findPropertyById(2);
		System.out.println(r);
	}
	
	
	
}
