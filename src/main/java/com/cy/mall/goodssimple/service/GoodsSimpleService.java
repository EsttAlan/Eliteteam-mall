package com.cy.mall.goodssimple.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.mall.goodssimple.pojo.GoodsSimplePojo;
import com.cy.mall.goodssimple.pojo.PageObject;
import com.github.pagehelper.Page;

public interface GoodsSimpleService {
	/**
	 * 根据keywords获得总记录数
	 * 根据keywords,orderOption,当前页数pageCurrent,获得当前页面记录list(页面大小先默认值)
	 * 然后最终将list,pageCurrent,pageSize,pageCounts,rowsCount,starIndex封装成对象
	 */
	PageObject<GoodsSimplePojo> showGoods(String keyWords,String orderOption,Integer pageCurrent,String type);
}
