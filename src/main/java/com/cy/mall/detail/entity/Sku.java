package com.cy.mall.detail.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Sku implements Serializable{
	
	private static final long serialVersionUID = -4210085239466463172L;
	
	private Integer skuId;
	private Integer spuId;
	private String name;
	private Double price;
	private Integer reserve;
	private String img;
	
}
