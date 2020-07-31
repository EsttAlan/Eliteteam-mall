package com.cy.mall.detail.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Values implements Serializable {
	
	private static final long serialVersionUID = 7112548955092233112L;
	@JsonProperty("AttributeValueId")
	private Integer attributeValueId;
	@JsonProperty("AttributeValue")
	private String attributeValue;
}
