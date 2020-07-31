package com.cy.mall.detail.bo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Property implements Serializable {

	private static final long serialVersionUID = -3889483722775628698L;
	private Integer id;
	private String name;
	private String state;
	private List<Option> optionList;
}
