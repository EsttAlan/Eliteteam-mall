package com.cy.mall.detail.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data

public class SkuType implements Serializable{
	
	@JsonProperty("AttributeId")
	private Integer attributeId;
	@JsonProperty("Attribute")
	private String attribute;
	@JsonProperty("AttributeValues")
	private List<Values> attributeValues;
	
}
