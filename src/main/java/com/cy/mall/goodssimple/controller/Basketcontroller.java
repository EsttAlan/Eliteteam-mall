package com.cy.mall.goodssimple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.mall.common.vo.JsonResult;
import com.cy.mall.goodssimple.pojo.Address;
import com.cy.mall.goodssimple.pojo.Baskets;
import com.cy.mall.goodssimple.pojo.order;
import com.cy.mall.goodssimple.service.basketService;
import com.cy.mall.goodssimple.service.impl.orderSerciceImpl;



@Controller
@RequestMapping("/basket/")
public class Basketcontroller {
	@Autowired
	private basketService serviceIimol;
	@Autowired
	private orderSerciceImpl osi;
//	@RequestMapping("FindAll")
//	public String FindAll(Model model) {
//		List<Baskets> list = serviceIimol.FindAll();
//		model.addAttribute("list", list);
//		return "basket";
//	}
	@RequestMapping("finshorder")
	public String forder() {
		return "finshorder";
	}
	
	@RequestMapping("deleteId")
	@ResponseBody
	public JsonResult dodeleteById(Integer id) {
		osi.dodeletebyid(id);
		return new JsonResult("delete ok");
	}
	@RequestMapping("order")
	public String order(Model model) {
		List<order> list = osi.doFindorder();
		model.addAttribute("list",list);
		return "order";
	}
	
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "basket";
	}
	@RequestMapping("FindAll")
	@ResponseBody
	public List FindAll() {
		List<Baskets> list = serviceIimol.FindAll();
		return list;
	}
	@RequestMapping("deletebyId")
	public String deletebyId(Integer id) {
		int rows= serviceIimol.DeleteById(id);
		return "forward:order";
	}
	@RequestMapping("deleteById")
	@ResponseBody
	public JsonResult deleteById(Integer id) {
		int rows= serviceIimol.DeleteById(id);
		return new JsonResult("delete ok");
	}
	
	@RequestMapping("add")
	public String save(Baskets basket) {
		int rows = serviceIimol.Add(basket);
		return"redirect:FindAll";
	}
	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Integer...ids) {
		serviceIimol.Delete(ids);
		return new JsonResult("delete ok");
	}
	@RequestMapping("Update")
	/*@ResponseBody
	public void  Updateadd(Baskets basket) {
		serviceIimol.Update(basket);
		//return "修改成功";
	}*/
	@ResponseBody
	public JsonResult Updateadd(Baskets basket) {
		serviceIimol.Update(basket);
		return new JsonResult("update ok");
	}
	/*@RequestMapping("Update2")
	public String Updatejian(Baskets basket) {
		serviceIimol.Update2(basket);
		return"forward:FindAll";
	}*/
	@RequestMapping("doFindByname")
	@ResponseBody
	public List<Baskets> doFindByname(String name) {
		return serviceIimol.doFindByname(name);
	}
	
	@RequestMapping("address")
	@ResponseBody
	public List address(Model model) {
		List<Address> ads = osi.doFindAddess();

		return ads;
	}
	@RequestMapping("doDeleteById")
	public String doDeleteById(Integer id) {
		System.out.println(id);
		int rows2 = osi.deleteById(id);
		return "forward:order"; 
	}

	@RequestMapping("doSaveAddress")
	@ResponseBody //spring mvc 会将返回的对象转换为json格式字符串
	public Address doSaveActivity(Address entity) {
		Address ads = osi.doSaveActivity(entity);
		System.out.println(ads);
		return ads;
	}
}
