package com.cy.mall.goodssimple.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Eliteteam implements Serializable{
	


	private static final long serialVersionUID = 6294707900505709384L;
private Integer id;
private String username;
private String password;
private String number;
private String salt;
}
