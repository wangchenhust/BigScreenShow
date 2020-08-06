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
	@CacheEvict(cacheNames = "panel",allEntries = true)
	public void delPanelCache() {}
	
	/*
	 * 删除柱状图缓存
	 * */
	@CacheEvict(cacheNames = "bar",allEntries = true)
	public void delBarCache() {}
	
	/*
	 * 删除饼图缓存
	 * */
	@CacheEvict(cacheNames = "pie",allEntries = true)
	public void delPieCache() {}
	
	/*
	 * 删除折线图缓存
	 * */
	@CacheEvict(cacheNames = "line",allEntries = true)
	public void delLineCache() {}
	
	/*
	 * 删除雷达图缓存
	 * */
	@CacheEvict(cacheNames = "rada",allEntries = true)
	public void delRadaCache() {}
	
	/*
	 * 删除折线图（可配置）缓存
	 * */
	@CacheEvict(cacheNames = "cline",allEntries = true)
	public void delCLineCache() {}
	
	/*
	 * 删除雷达图（可配置）缓存
	 * */
	@CacheEvict(cacheNames = "crada",allEntries = true)
	public void delCRadaCache() {}
	
	/*
	 * 删除列表缓存
	 * */
	@CacheEvict(cacheNames = "list",allEntries = true)
	public void delListCache() {}
	
	
}