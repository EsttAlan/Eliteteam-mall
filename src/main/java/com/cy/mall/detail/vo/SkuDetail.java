package com.cy.mall.detail.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuDetail {
	private String name;
	private Double price;
	private String img;
	private List<SkuType> skuType;
}
