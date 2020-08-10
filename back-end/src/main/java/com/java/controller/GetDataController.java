package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.java.service.impl.GetDataImpl;

/**
 * for：控制层，面向json字符串传输
 * */

@RestController
public class GetDataController {
	@Autowired GetDataImpl getDataImpl;
	
	@GetMapping("GetData/{type}")
	public String getData(@PathVariable(name = "type") String type) {
		switch (type) {
		case "Panel":
			return getDataImpl.getPanelData();
		case "Bar":
			return getDataImpl.getBarData();
		case "Pie":
			return getDataImpl.getPieData();
		case "Line":
			return getDataImpl.getLineData();
		case "Rada":
			return getDataImpl.getRadaData();
		case "CLine":
			return getDataImpl.getCLineData();
		case "CRada":
			return getDataImpl.getCRadaData();
		case "MList":
			return getDataImpl.getMListData();
		default:
			return null;
		}
	}
	

}
