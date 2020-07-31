package com.cy.mall.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonResult {
	/**消息状态码*/
	private Integer state;
	/**状态码对应的具体信息*/
	private String message;
	/**数据(基于此属性封装业务层返回的数据)*/
	private Object data;
	
	public JsonResult(String message) {
		this.state=1;
		this.message=message;
	}
	
	public JsonResult(Object data) {
		this.state=1;
		this.data=data;
	}
	
	public JsonResult(Throwable e) {
		this.state=0;
		this.message=e.getMessage(); //异常信息
	}
}
