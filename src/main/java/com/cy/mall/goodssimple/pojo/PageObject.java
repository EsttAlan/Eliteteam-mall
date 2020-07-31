package com.cy.mall.goodssimple.pojo;//business object
import java.io.Serializable;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageObject<T> implements Serializable{
	  private static final long serialVersionUID = -553542799593996985L;
	  /**当前页记录*/
      private List<T> records;
      /**总行数(通过查询获得)*/
      private Integer rowCount;
      /**总页数*/
      private Integer pageCount;
      /**每页要显示多少条记录*/
      private Integer pageSize;
      /**当前页的页码值*/
      private Integer pageCurrent;
}
