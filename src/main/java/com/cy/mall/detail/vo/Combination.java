package com.cy.mall.detail.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Combination {
	private Integer skuId;
	@JsonProperty("Attributes")
	private String attributes;
}
