package com.java.server;

import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.java.utils.RedisClearUtils;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.context.CanalContext;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.model.CanalModel;

@Component
@CanalTable(value = "all")		//这里写关联的数据表名，all表示所有
public class CanalServer implements EntryHandler<Map<String, String>> {
	@Autowired RedisClearUtils redisClearUtils;
	
	
	private static final Logger log = LoggerFactory.getLogger(CanalServer.class);

    @Override
    public void insert(Map<String, String> map) {
    	CanalModel canal = CanalContext.getModel();
    	if(!canal.getDatabase().equals("bigscreen"))return;
        log.info("插入了一条数数据：{}"+map);
        //sse群发
		//SseEmitterServer.batchSendMessage(JSON.toJSONString(tm.getNum()));
    }

    @Override
    public void update(Map<String, String> before, Map<String, String> after) {
    	CanalModel canal = CanalContext.getModel();
    	if(!canal.getDatabase().equals("bigscreen"))return;
    	log.info("更新前：{}更新后：{}",before,after);

    	//sse群发
		//SseEmitterServer.batchSendMessage(JSON.toJSONString(tm.getNum()));

    }
    @Override
    public void delete(Map<String, String> map) {
    	CanalModel canal = CanalContext.getModel();
    	if(!canal.getDatabase().equals("bigscreen"))return;
    	log.info("删除了一条数数据：{}"+map);
    	//sse群发
		//SseEmitterServer.batchSendMessage(JSON.toJSONString(tm.getNum()));

    }
}
