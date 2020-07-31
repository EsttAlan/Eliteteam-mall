package com.cy.mall.detail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.mall.common.vo.JsonResult;
import com.cy.mall.detail.bo.Goods;
import com.cy.mall.detail.service.DetailService;
import com.cy.mall.detail.vo.Combination;
import com.cy.mall.detail.vo.SkuDetail;
import com.cy.mall.detail.vo.SkuType;

//@RestController
@Controller
@RequestMapping("/detail/")
public class DetailController {
	
	@Autowired
	private DetailService detailService;
	
	@ResponseBody
	@RequestMapping("doAddToBasket")
	public JsonResult doAddToBasket(Integer skuId, Integer number) {
		int rows = detailService.doAddToBasket(skuId, number);
		return new JsonResult(rows);
	}
	
	@ResponseBody
	@RequestMapping("getCombination")
	public JsonResult getCombination(Integer skuId) {
		List<Combination> combination = detailService.getCombination(skuId);
		return new JsonResult(combination);
	}
	
	@ResponseBody
	@RequestMapping("reloadSku")
	public JsonResult reloadSku(Integer skuId) {
		Goods goods = detailService.findGoodsBySkuId(skuId);
		return new JsonResult(goods);
	}
	
	@ResponseBody
	@RequestMapping("findSkuDetailBySkuId")
	public JsonResult findSkuTypeBySkuId(Integer skuId) {
		Goods goods = detailService.findGoodsBySkuId(skuId);
		SkuDetail skuDetail = detailService.goodsToSkyType(goods);
		return new JsonResult(skuDetail);
	}
	
	//根据skuid查询得到商品名称,商品价格,商品属性列表及每个属性对应的选项列表
	@ResponseBody
	@RequestMapping("findGoodsBySkuId")
	public JsonResult findGoodsBySkuId(Integer skuId) {
		Goods goods = detailService.findGoodsBySkuId(skuId);
		return new JsonResult(goods);
	}
	
	@RequestMapping("GoodsDetail")
	public String doFindBySkuId(Integer skuId,Model model) {
		model.addAttribute("skuId", skuId);
		return "index";
	}
	
	@RequestMapping("test")
	public String test() {
		
		return "PagePrincipal2";
	}
}
