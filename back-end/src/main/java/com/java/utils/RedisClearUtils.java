package com.java.utils;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;

/*
 * for：清除redis指定组缓存
 * */

@CacheConfig()
public class RedisClearUtils {
	/*
	 * 删除仪表盘图缓存
	 * */
	@CacheEvict(cacheNames = "Panel",allEntries = true)
	public void delPanelCache() {}
	
	/*
	 * 删除柱状图缓存
	 * */
	@CacheEvict(cacheNames = "Bar",allEntries = true)
	public void delBarCache() {}
	
	/*
	 * 删除饼图缓存
	 * */
	@CacheEvict(cacheNames = "Pie",allEntries = true)
	public void delPieCache() {}
	
	/*
	 * 删除折线图缓存
	 * */
	@CacheEvict(cacheNames = "Line",allEntries = true)
	public void delLineCache() {}
	
	/*
	 * 删除雷达图缓存
	 * */
	@CacheEvict(cacheNames = "Rada",allEntries = true)
	public void delRadaCache() {}
	
	/*
	 * 删除可配置图表缓存
	 * */
	@CacheEvict(cacheNames = "ConfigData",allEntries = true)
	public void delConfigDataCache() {}
	
	/*
	 * 删除列表缓存
	 * */
	@CacheEvict(cacheNames = "MList",allEntries = true)
	public void delMListCache() {}
	
	/*
	 * 删除地图缓存
	 * */
	@CacheEvict(cacheNames = "MMap",allEntries = true)
	public void delMMapCache() {}
	
	/*
	 * 删除风险阈值缓存
	 * */
	@CacheEvict(cacheNames = "Risk",allEntries = true)
	public void delRiskCache() {}
	
}