package com.cy.mall.goodssimple.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class order {
	private Long id;
	private String name;
	private String model;
	private Double money;
	private Integer number;
}
