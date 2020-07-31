package com.cy.mall.detail.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuBasket implements Serializable {
	
	private static final long serialVersionUID = 4245944563919518499L;
	private Integer id;
	private String skuName;
	private Integer skuId;
	private String model;
	private Double skuPrice;
	private Integer number;
	private Integer userId;
	private String img;
	
}
