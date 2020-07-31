package com.cy.mall.detail.bo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Option implements Serializable {
	
	private static final long serialVersionUID = -6441986017484462484L;
	private Integer id;
	private String name;
	private Integer state;
	private String img;
}
