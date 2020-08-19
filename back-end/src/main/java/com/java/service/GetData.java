package com.java.service;

/**
 * for：服务层接口
 * */

public interface GetData {
	
	public String getPanelData();
	
	public String getBarData();
	
	public String getPieData();
	
	public String getLineData();
	
	public String getRadaData();
	
	public String getCLineData(String bank);
	
	public String getCRadaData(String bank);
	
	public String getMListData();
	
	public String getMMapData();
	
	public String getRiskData();

}
