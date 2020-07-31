package com.cy.mall.goodssimple.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSimplePojo implements Serializable{
	private static final long serialVersionUID = 7135889389862691777L;
	private Integer id;
	private String name;
	private Double price;
	private String type;
	private Integer salnumber;
	private Date createdTime;
}
