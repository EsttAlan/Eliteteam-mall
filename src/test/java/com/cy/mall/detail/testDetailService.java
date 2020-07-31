package com.cy.mall.detail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.mall.detail.bo.Goods;
import com.cy.mall.detail.service.DetailService;
import com.cy.mall.detail.vo.Combination;

@SpringBootTest
public class testDetailService {
	
	@Autowired
	private DetailService detailService;
	
	@Test
	public void doAddToBasket() {
		int rows = detailService.doAddToBasket(1,1);
		System.out.println("影响行数:"+rows);
	}
	
	@Test
	public void detailServiceTests() {
		Goods goods = detailService.findGoodsBySkuId(1);
		System.out.println(goods);
	}
	
	@Test
	public void getCombination() {
		List<Combination> cmbnList = detailService.getCombination(1);
		for (Combination combination : cmbnList) {
			System.out.println(combination);
		}
	}
}
