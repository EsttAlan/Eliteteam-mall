package com.cy.mall.goodssimple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.mall.common.vo.JsonResult;
import com.cy.mall.goodssimple.service.impl.GoodsSimpleServiceImpl;



@Controller
@RequestMapping("/mall")
public class GoodsSimpleController {
	@Autowired 
	private GoodsSimpleServiceImpl goodsSimpleServiceImpl;
	@RequestMapping("/GoodsPageData")
	@ResponseBody
	public JsonResult findGoodsPageData(Model model,String keyWords, String orderOption, Integer pageCurrent,String type) {
		return new JsonResult(goodsSimpleServiceImpl.showGoods(keyWords, orderOption, pageCurrent,type));
	}
	@RequestMapping("/PagePrincipal")
	public String findZhuye() {
		return "PagePrincipal";
	}
//	@RequestMapping("/GoodsDetail")
//	public String findZhuye1(Model model,Integer skuId) {
//		model.addAttribute(skuId);
//		return "GoodsDetail";
//	}
	@RequestMapping("/GoodsPageDataList")
	public String GoodsPage(Model model,String keyWords,String type) {
		if(!StringUtils.isEmpty(keyWords)) {
			model.addAttribute("keyWords", keyWords);
		}
		if(!StringUtils.isEmpty(type)) {
			model.addAttribute("type", type);
		}
		return "PageData";
	}
	
	
}
