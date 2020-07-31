package com.cy.mall.detail.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.cy.mall.detail.bo.Goods;
import com.cy.mall.detail.vo.Combination;
import com.cy.mall.detail.vo.SkuDetail;
import com.cy.mall.detail.vo.SkuType;

public interface DetailService {
	public Goods findGoodsBySkuId(Integer skuId);
	public SkuDetail goodsToSkyType(Goods goods);
	public List<Combination> getCombination(Integer skuId);
	public int doAddToBasket(Integer skuId, Integer number);
}
