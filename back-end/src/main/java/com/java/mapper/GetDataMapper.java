package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.pojo.Bar;
import com.java.pojo.CLine;
import com.java.pojo.CRada;
import com.java.pojo.Line;
import com.java.pojo.MList;
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
	
	public List<CLine> getCLineData();
	
	public List<CRada> getCRadaData();
	
	public List<MList> getMListData(); 
}
