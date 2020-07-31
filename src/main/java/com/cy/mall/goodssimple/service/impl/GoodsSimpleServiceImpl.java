package com.cy.mall.goodssimple.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.mall.annotation.RequiredCache;
import com.cy.mall.goodssimple.dao.GoodsSimpleDao;
import com.cy.mall.goodssimple.pojo.GoodsSimplePojo;
import com.cy.mall.goodssimple.pojo.PageObject;
import com.cy.mall.goodssimple.service.GoodsSimpleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class GoodsSimpleServiceImpl implements GoodsSimpleService{
	static Set<String> OptionSet = new HashSet<String>();
	static Set<String> TypeSet = new HashSet<String>();
	static {
		OptionSet.add("id");
		OptionSet.add("price");
		OptionSet.add("salnumber");
		OptionSet.add("createdTime");
		TypeSet.add("all");
		TypeSet.add("mobilePhone");
		TypeSet.add("clothes");
	}
	@Autowired
	GoodsSimpleDao goodsSimpleDao;
	
	
	@RequiredCache
	@Override
	public PageObject<GoodsSimplePojo> showGoods(String keyWords, String orderOption, 
			Integer pageCurrent,String type) {
		System.out.println("===serviveImpl===");
		//1.先进行数据校验
		//1.1)页码数据校验
		if(pageCurrent==null||pageCurrent<1) {
			throw new IllegalArgumentException("页码不合法");
		}
		//1.2)排序数据校验
		if(orderOption!=null&&OptionSet.add(orderOption.trim())) {
			//这里会进行空格处理,排除用户多打了
			throw new IllegalArgumentException("输入的排序字段不合法");
		}
		//1.3)类型数据校验,这个主要是防直接用方法url进来的非法访问
		if(type!=null&&TypeSet.add(type.trim())) {
			//这里会进行空格处理,排除用户多打了
			throw new IllegalArgumentException("无此类型的商品信息");
		}
		//2.然后设置参数
		Integer pageSize=12;
		//1)页面大小及当前页数,以及排序
		Page<GoodsSimplePojo> startPage = PageHelper.startPage(pageCurrent,pageSize);
		startPage.setOrderBy(orderOption+" desc");
		//3.启动基于pageHelper查询
		if(type!=null&&type.trim().equals("all")) {
			type=null;//解决的是将传入查全部进行一个转换
		}
		List<GoodsSimplePojo> records = goodsSimpleDao.ListGoodsSimple(keyWords,type);
		//4.再次进行校验
		if(startPage.getTotal()==0) {
			throw new IllegalArgumentException("没有查到符合条件的记录");
		}
		//5.最后将页面信息及查询的结果封装传递
		System.out.println(startPage);
		return new PageObject(records,(int)startPage.getTotal(),(int)startPage.getPages(),pageSize,pageCurrent);
	}
}
