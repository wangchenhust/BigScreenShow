package com.java.pojo;

import java.math.BigDecimal;

public class Pie {
	
	private BigDecimal value;
	
	private String name;
	
	private String valuedate;




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValuedate() {
		return valuedate;
	}

	public void setValuedate(String valuedate) {
		this.valuedate = valuedate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
