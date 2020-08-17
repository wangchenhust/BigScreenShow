package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.java.pojo.Bar;
import com.java.pojo.CLine;
import com.java.pojo.CRada;
import com.java.pojo.Line;
import com.java.pojo.MList;
import com.java.pojo.MMap;
import com.java.pojo.Panel;
import com.java.pojo.Pie;
import com.java.pojo.Rada;

@Mapper
public interface GetDataMapper {
	
	public List<Panel> getPanelData();
	
	public List<Bar> getBarData();
	
	public List<Pie> getPieData();
	
	public List<Line> getLineData();
	
	public List<Rada> getRadaData();
	
	public List<CLine> getCLineData(@Param("bank") String bank);
	
	public List<CRada> getCRadaData(@Param("bank") String bank);
	
	public List<MList> getMListData(); 
	
	public List<MMap> getMMapData();
}
