package com.cy.mall.detail.bo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Goods implements Serializable{
	
	private String name;
	private Double price;
	private String img;
	private List<Property> propertyList;
	
}
