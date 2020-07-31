package com.cy.mall.goodssimple.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Baskets {
	private Long id;
	private String skuName;
	private String model;
	private Double skuPrice;
	private Integer number;
	private Integer userId;
	private String img;
	private Integer skuId;
	@Override
	public String toString() {
		return "Baskets [id=" + id + ", skuName=" + skuName + ", model=" + model + ", skuPrice=" + skuPrice
				+ ", number=" + number + ", userId=" + userId + ", img=" + img + ", skuId=" + skuId + "]";
	}
	
	
	
}
