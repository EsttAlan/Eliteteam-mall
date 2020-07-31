package com.cy.mall.detail.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cy.mall.detail.bo.Option;
import com.cy.mall.detail.bo.Property;
import com.cy.mall.detail.entity.Sku;
import com.cy.mall.detail.entity.SkuBasket;
import com.cy.mall.detail.entity.SkuOption;
import com.cy.mall.detail.entity.Spu;

@Mapper
public interface DetailDao {
	
	//根据optionId List查询对应的option名称
	List<String> findOptionNameById(List<Integer> optionIdList);
	
	//根据skuId查询库存
	Integer findReserveBySkuId(Integer skuId);
	
	//根据skuId查询购物车表里是否已存在
	@Select("select count(*) from sku_basket where skuId=#{skuId}")
	Integer isExisted(Integer skuId);
	
	//添加新纪录到购物车
	int insertObject(SkuBasket entity);
	
	//更新购物车旧记录
	int updateBasket(Integer skuId, Integer number);
	
	//根据skuId查询得到所属spu的所有option
	List<SkuOption> findAllOptionsBySkuId(Integer skuId);
	
	//根据skuId查询得到sku表对应的entity
	Sku findSkuBySkuId(Integer skuId);
	
	//根据propertyId查询得到Option对象
	List<Option> findOptionsByPropertyId(Integer propertyId);
	
	//根据skuid查询得到对应的property id
	List<Integer> findPropertyIdBySkuId(Integer skuId);
	
	//根据id嵌套查询得到对应的property对象
	Property findPropertyById(Integer id);

}