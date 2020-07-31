package com.cy.mall.goodssimple.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.mall.goodssimple.pojo.Address;
import com.cy.mall.goodssimple.pojo.order;



@Mapper
public interface orderDao {
	@Select("select * from sku_basket")
	List<order> doFindorder();

	@Delete("delete from basket where id=#{id}")
	int dodeletebyid(Integer id);

	@Select("select * from address")
	List<Address> doFindAddess();
	int insertActivity(Address entity);
	@Delete("delete from address where id=#{id}")

	int deleteById(Integer id);
	@Select("select * from address where id=#{id}")
	Address findById(Integer id);

}
