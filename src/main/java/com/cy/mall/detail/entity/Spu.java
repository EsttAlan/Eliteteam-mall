package com.cy.mall.detail.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Spu implements Serializable {

	private static final long serialVersionUID = 6960438837693729358L;
	private Integer spuId;
	private String name;
	private String description;
	private String spuCode;
	private Integer brandId;
	private Integer categoryId;
	private String propertyList;
}
