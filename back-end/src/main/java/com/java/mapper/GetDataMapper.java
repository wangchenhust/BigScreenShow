package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.pojo.Bar;
import com.java.pojo.ConfigData;
import com.java.pojo.Line;
import com.java.pojo.MList;
import com.java.pojo.MMap;
import com.java.pojo.Panel;
import com.java.pojo.Pie;
import com.java.pojo.Rada;
import com.java.pojo.Risk;

@Mapper
public interface GetDataMapper {
	
	public List<Panel> getPanelData();
	
	public List<Bar> getBarData();
	
	public List<Pie> getPieData();
	
	public List<Line> getLineData();
	
	public List<Rada> getRadaData();
	
	public List<MList> getMListData(); 
	
	public List<MMap> getMMapData();
	
	public List<Risk> getRiskData();
	
	public List<ConfigData> getConfigData();
}
