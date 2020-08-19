package com.java.server;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.java.service.impl.GetDataImpl;
import com.java.utils.RedisClearUtils;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.context.CanalContext;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.model.CanalModel;

@Component
@CanalTable(value = "all")		//这里写关联的数据表名，all表示所有
public class CanalServer implements EntryHandler<Map<String, String>> {
	@Autowired RedisClearUtils redisClearUtils;
	@Autowired GetDataImpl getDataImpl;
	
	
	private static final Logger log = LoggerFactory.getLogger(CanalServer.class);

    @Override
    public void insert(Map<String, String> map) {
    	CanalModel canal = CanalContext.getModel();
    	if(!canal.getDatabase().equals("bigscreen"))return;
        log.info("插入了一条数数据：{}"+map);
        //sse根据类型群发
        batchMessage(canal, map);
    }

    @Override
    public void update(Map<String, String> before, Map<String, String> after) {
    	CanalModel canal = CanalContext.getModel();
    	if(!canal.getDatabase().equals("bigscreen"))return;
    	log.info("更新前：{}更新后：{}",before,after);
    	//sse根据类型群发
    	batchMessage(canal, after);

    }
    
    @Override
    public void delete(Map<String, String> map) {
    	CanalModel canal = CanalContext.getModel();
    	if(!canal.getDatabase().equals("bigscreen"))return;
    	log.info("删除了一条数数据：{}"+map);
    	//sse根据类型群发
    	batchMessage(canal, map);

    }
    
    //根据改动的表群发消息
    public void batchMessage(CanalModel canal,Map<String,String> map) {
    	log.info("改动的表{}",canal.getTable());
    	switch (canal.getTable()) {
		case "pievalues":
			redisClearUtils.delPieCache();
			SseEmitterServer.batchSendMessage(getDataImpl.getPieData(),"pievalues");
			break;
		case "barvalues":
			redisClearUtils.delBarCache();
			SseEmitterServer.batchSendMessage(getDataImpl.getBarData(), "barvalues");
			break;
		case "panelvalues":
			redisClearUtils.delPanelCache();
			SseEmitterServer.batchSendMessage(getDataImpl.getPanelData(), "panelvalues");
			break;
		case "entryvalues":
			redisClearUtils.delMListCache();
			SseEmitterServer.batchSendMessage(getDataImpl.getMListData(), "entryvalues");
			break;
		case "mapvalues":
			redisClearUtils.delMMapCache();
			SseEmitterServer.batchSendMessage(getDataImpl.getMMapData(), "mapvalues");
			break;
		case "linevalues":
			redisClearUtils.delLineCache();
			SseEmitterServer.batchSendMessage(getDataImpl.getLineData(), "linevalues");
			break;
		case "radarvalues":
			redisClearUtils.delRadaCache();
			SseEmitterServer.batchSendMessage(getDataImpl.getRadaData(), "radavalues");
			break;
		case "peizhivalues":
			redisClearUtils.delConfigDataCache();
			SseEmitterServer.batchSendMessage(getDataImpl.getConfigData(), "peizhivalues");
		default:
			return;
		}
    }
}
