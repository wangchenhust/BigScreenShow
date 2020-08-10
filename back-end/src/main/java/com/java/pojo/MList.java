package com.java.pojo;

import java.math.BigDecimal;

public class MList {
	private String bank;
	
	private Integer growth_rate;
	
	private BigDecimal total_assets_2019;
	
	private BigDecimal total_assets_2020;
	
	private String type;

	public Integer getGrowth_rate() {
		return growth_rate;
	}

	public void setGrowth_rate(Integer growth_rate) {
		this.growth_rate = growth_rate;
	}

	public BigDecimal getTotal_assets_2019() {
		return total_assets_2019;
	}

	public void setTotal_assets_2019(BigDecimal total_assets_2019) {
		this.total_assets_2019 = total_assets_2019;
	}

	public BigDecimal getTotal_assets_2020() {
		return total_assets_2020;
	}

	public void setTotal_assets_2020(BigDecimal total_assets_2020) {
		this.total_assets_2020 = total_assets_2020;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
