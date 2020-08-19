package com.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.java.mapper.GetDataMapper;
import com.java.service.GetData;
import com.java.utils.UrlNameChangeUtils;

/**
 * for：服务层实现，从mapper获取数据，返回json字符串
 * */

@Service
@CacheConfig()
public class GetDataImpl implements GetData{
	@Autowired GetDataMapper mapper;

	@Override
	@Cacheable(cacheNames = "Panel")
	public String getPanelData() {
		String jsonString=JSON.toJSONString(mapper.getPanelData());
		return jsonString;
	}

	@Override
	@Cacheable(cacheNames = "Bar")
	public String getBarData() {
		String jsonString=JSON.toJSONString(mapper.getBarData());
		return jsonString;
	}

	@Override
	@Cacheable(cacheNames = "Pie")
	public String getPieData() {
		String jsonString=JSON.toJSONString(mapper.getPieData());
		return jsonString;
	}

	@Override
	@Cacheable(cacheNames = "Line")
	public String getLineData() {
		String jsonString=JSON.toJSONString(mapper.getLineData());
		return jsonString;
	}

	@Override
	@Cacheable(cacheNames = "Rada")
	public String getRadaData() {
		String jsonString=JSON.toJSONString(mapper.getRadaData());
		return jsonString;
	}

	/**
	 * @param bank 银行名称的id
	 * */
	@Override
	@Cacheable(cacheNames = "CLine",key = "#p0")
	public String getCLineData(String bank) {
		bank=UrlNameChangeUtils.bankNameChange(bank);
		if(bank==null)return null;
		String jsonString=JSON.toJSONString(mapper.getCLineData(bank));
		return jsonString;
	}

	/**
	 * @param bank 银行名称的id
	 * */
	@Override
	@Cacheable(cacheNames = "CRada",key = "#p0")
	public String getCRadaData(String bank) {
		bank=UrlNameChangeUtils.bankNameChange(bank);
		if(bank==null)return null;
		String jsonString=JSON.toJSONString(mapper.getCRadaData(bank));
		return jsonString;
	}

	@Override
	@Cacheable(cacheNames = "MList")
	public String getMListData() {
		String jsonString=JSON.toJSONString(mapper.getMListData());
		return jsonString;
	}

	@Override
	@Cacheable(cacheNames = "MMap")
	public String getMMapData() {
		String jsonString=JSON.toJSONString(mapper.getMMapData());
		return jsonString;
	}

	@Override
	@Cacheable(cacheNames = "Risk")
	public String getRiskData() {
		String jsonString=JSON.toJSONString(mapper.getRiskData());
		return jsonString;
	}
	
	

}
