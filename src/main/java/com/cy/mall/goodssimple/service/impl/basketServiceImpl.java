package com.cy.mall.goodssimple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.mall.aspect.ClearCache;
import com.cy.mall.aspect.RequiredCache;
import com.cy.mall.common.exception.ServiceException;
import com.cy.mall.goodssimple.dao.BasketDao;
import com.cy.mall.goodssimple.pojo.Baskets;
import com.cy.mall.goodssimple.service.basketService;
@Service
public class basketServiceImpl implements basketService {
	@Autowired
	private BasketDao dao;
	@RequiredCache
	@Override
	public List<Baskets> FindAll() {
		return dao.FindAll();
	}
	@ClearCache
	@Override
	public int Delete(Integer... ids) {
		if(ids==null||ids.length==0)
		throw new ServiceException("数据不合法,id="+ids);
		int rows=dao.Delete(ids);
		if(rows==0)
		throw new ServiceException("此信息可能已经不存在");
		return rows;
	}
	@ClearCache
	@Override
	public int Add(Baskets baskets) {
		if(baskets==null)
		throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(baskets.getSkuName()))
			throw new ServiceException("部门不能为空");
		if(StringUtils.isEmpty(baskets.getImg()))
			throw new ServiceException("图片路径不能为空");
		if(StringUtils.isEmpty(baskets.getNumber()))
			throw new ServiceException("数量不能为空");
		if(StringUtils.isEmpty(baskets.getSkuPrice()))
			throw new ServiceException("单价不能为空");
			//2.保存数据
			int rows=dao.Add(baskets);	
		return rows;
	}

//	@Override
//	public int UpdateById(Baskets baskets) {
//		// TODO Auto-generated method stub
//		return dao.UpdateById(baskets);
//	}
	@ClearCache
	@Override
	public int DeleteById(Integer id) {
		if(id==null||id<=0)
			throw new ServiceException("数据不合法,id="+id);
			int rows=dao.DeleteById(id);
			if(rows==0)
			throw new ServiceException("此信息可能已经不存在");
			return rows;
	}
	@ClearCache
	@Override
	public int Update(Baskets baskets) {
			if(baskets==null)
			throw new ServiceException("保存对象不能为空");
			if(StringUtils.isEmpty(baskets.getId()))
				throw new ServiceException("Id不能为空");
			if(baskets.getNumber()<1)
				throw new ServiceException("number不能小于1");
			int rows;
			try{
				rows=dao.Update(baskets);
				}catch(Exception e){
				e.printStackTrace();
				throw new ServiceException("更新失败");
				}
				//3.返回数据
				return rows;
	}
	@ClearCache
	@Override
	public int Update2(Baskets baskets) {
		// TODO Auto-generated method stub
		return dao.Update2(baskets);
	}
	@ClearCache
	@Override
	public List<Baskets> doFindByname(String name) {
		return dao.doFindByname(name);
	}

}
