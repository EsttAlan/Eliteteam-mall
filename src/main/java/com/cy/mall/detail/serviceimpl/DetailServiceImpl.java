package com.cy.mall.detail.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.String;
import com.cy.mall.detail.bo.Goods;
import com.cy.mall.detail.bo.Option;
import com.cy.mall.detail.bo.Property;
import com.cy.mall.detail.dao.DetailDao;
import com.cy.mall.detail.entity.Sku;
import com.cy.mall.detail.entity.SkuBasket;
import com.cy.mall.detail.entity.SkuOption;
import com.cy.mall.detail.service.DetailService;
import com.cy.mall.detail.vo.Combination;
import com.cy.mall.detail.vo.SkuDetail;
import com.cy.mall.detail.vo.SkuType;
import com.cy.mall.detail.vo.Values;
import com.cy.mall.common.exception.ServiceException;
import com.simple.util.math.function.FactorialFunction;


@Service
public class DetailServiceImpl implements DetailService {
	
	@Autowired
	private DetailDao detailDao;
	
	//获得货品信息
	@Override
	public Goods findGoodsBySkuId(Integer skuId) {
		Goods goods = new Goods();
		//根据skuId查询得到sku表对应的entity
		Sku sku = detailDao.findSkuBySkuId(skuId);
		//设置Goods对象
		//1.设置set商品名称,价格
		goods.setName(sku.getName());
		goods.setPrice(sku.getPrice());
		goods.setImg(sku.getImg());
		//2.设置goods的List<property>
		//2.1创建List对象
		List<Property> propertyList = new ArrayList<>();
		//2.1 根据skuid查询得到对应的propertyId list
		List<Integer> propertyIds = detailDao.findPropertyIdBySkuId(skuId);
		for (Integer id : propertyIds) {
			Property e = detailDao.findPropertyById(id);
			propertyList.add(e);
		}
		goods.setPropertyList(propertyList);
		
		return goods;
	}
	
	//获得商品属性和属性列表
	@Override
	public SkuDetail goodsToSkyType(Goods goods) {
		
		List<SkuType> skuTypeList = new ArrayList<>();
		List<Property> propertyList = goods.getPropertyList();
		for (Property property : propertyList) {
			SkuType skuType = new SkuType();
			List<Values> valueList = new ArrayList<>();
			skuType.setAttributeId(property.getId());
			skuType.setAttribute(property.getName());
			List<Option> optionList = property.getOptionList();
			for(int i=0;i<optionList.size();i++) {
				Values value = new Values();
				Option option = optionList.get(i);
				value.setAttributeValueId(option.getId());
				value.setAttributeValue(option.getName());
				valueList.add(i, value);
			}
			skuType.setAttributeValues(valueList);
			skuTypeList.add(skuType);
			
		}
		
		return new SkuDetail(goods.getName(), goods.getPrice(),goods.getImg(), skuTypeList);
	}

	//获得所有商品组合
	@Override
	public List<Combination> getCombination(Integer skuId) {
		List<Combination> cmbnList = new ArrayList<>();
		HashSet<Integer> skuIds = new HashSet<>(); //存储skuId
		//根据skuId查询得到所属spu的所有option
		List<SkuOption> allOption = detailDao.findAllOptionsBySkuId(skuId);
		for (SkuOption skuOption : allOption) { //去重得到spu的id列表
			skuIds.add(skuOption.getSkuId());
		}
		//通过skuid取对应的所有属性
		for (Integer id : skuIds) {
			
			List<Integer> idList = new ArrayList<>();
			
			for(int i=0;i<allOption.size();i++) {
				
				SkuOption skuOption = allOption.get(i);
				
				int a=id;
				int b=skuOption.getSkuId();
				
				if(id==skuOption.getSkuId()) {
					idList.add(skuOption.getOptionId());
				}
			}
			//生成所有组合
			List<Integer[]> complexList = FactorialFunction.permutations(idList);
			//添加数据到cmbn
			for (Integer[] arr : complexList) {
				Combination cmbn = new Combination();
				cmbn.setSkuId(id);
				String attributes=",";
				for (Integer i : arr) {
					attributes+= i + ",";
				}
				cmbn.setAttributes(attributes);
				cmbnList.add(cmbn);
			}
		}
		return cmbnList;
	}
	
	//加入购物车
	@Override
	public int doAddToBasket(Integer skuId, Integer number) {
		
		//1.参数校验
		if(skuId==null||skuId<1)
			throw new IllegalArgumentException("id值无效");
		if(number==null||number<1)
			throw new IllegalArgumentException("商品数量无效");
		//2.基于skuId获取库存数并进行校验
		Integer reserve = detailDao.findReserveBySkuId(skuId);
		if(reserve<=0)
			throw new ServiceException("该商品库存不足");
		
		Integer res = detailDao.isExisted(skuId);
		if(res<0||res>1)
			throw new ServiceException("购物车数据异常");
		
		//如果没有记录就插入新纪录
		if(res==0) {
			//通过skuId查询得到sku
			Sku sku = detailDao.findSkuBySkuId(skuId);
			//查询其他数据,封装成SkuBasket对象
			Integer id=null;
			String skuName=sku.getName();
			//1.获取对应spu所有的options
			List<SkuOption> allOptionsList = detailDao.findAllOptionsBySkuId(skuId);
			//2.sku对应的属性id值集合
			List<Integer> optionIds = new ArrayList<>();
			//将1.中的对应optionid解析到2.中
			for (SkuOption skuOption : allOptionsList) {
				if(skuId==skuOption.getSkuId()) {
					Integer optionId = skuOption.getOptionId();
					optionIds.add(optionId);
				}
			}
			//获取属性名称
			List<String> optionNames = detailDao.findOptionNameById(optionIds);
			//将属性名称组合成字符串
			String model="";
			for (String element : optionNames) {
				model+=element+" "; 
			}
			
			Double skuPrice=sku.getPrice();
			Integer userId=null; //待完善
			
			String imgs = sku.getImg();
			//把json字符串列表转为json对象列表
			JSONArray jsonObj = new JSONArray(imgs);
			//将json对象列表存入list集合
			List<Object>  list = jsonObj.toList();
			//取出首个元素
			Object obj=list.get(0);
			Map<String,Object> map = (HashMap<String,Object>)obj;
			String img=(String) map.get("imgS");
		
			SkuBasket basket = new SkuBasket(id, skuName, skuId, model, skuPrice, number, userId, img);
			int rows = detailDao.insertObject(basket);
			return rows;
		}
		
		//如果有记录就更新该记录
		if(res==1) {
			int rows = detailDao.updateBasket(skuId,number);
			return rows;
		}
		
		throw new ServiceException("其他错误");
	}

}

