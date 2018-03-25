package com.taotao.result;

import java.io.Serializable;
import java.util.List;

public class EasyUIDataResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String total;
	private List   rows;
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}
