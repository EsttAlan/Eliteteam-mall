package com.cy.mall.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONArray;

public class testJson {
	public static void main(String[] args) {
		String imgs = "[{\"imgB\":\"iphone11_black_01\",\"imgS\":\"iphone11_black_01s\"},{\"imgB\":\"iphone11_black_02\",\"imgS\":\"iphone11_black_02s\"}]";
		System.out.println(imgs);
		JSONArray jsonObj = new JSONArray(imgs);
		List<Object>  list = jsonObj.toList();
		System.out.println("list:++"+list);
//		for(Object obj :list){
//			Map<String,Object> map = (HashMap<String,Object>)obj;
//			System.out.println("map.."+map.get("imgS"));
//			
//			Set<String> sets = map.keySet();
//			System.out.println("ssss+"+sets);
//			for(String key:sets){
//				System.out.println(key+": ++"+map.get(key));
//			}
//			System.out.println();
//		}
//		
		Object obj=list.get(0);
		Map<String,Object> map = (HashMap<String,Object>)obj;
		System.out.println(map.get("imgS"));
		
	}
}
