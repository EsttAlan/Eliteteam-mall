package com.cy.mall.detail.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class SkuOption implements Serializable {
	
	private static final long serialVersionUID = 2944755688718197129L;
	private Integer id;
	private Integer skuId;
	private Integer optionId;
}
