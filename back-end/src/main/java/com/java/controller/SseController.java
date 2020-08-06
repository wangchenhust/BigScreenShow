package com.java.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.java.server.SseEmitterServer;



@RestController
public class SseController {

	/**
     * 用于创建sse连接
     * 
     * @param id 用户id
     */
	@ResponseBody
	@GetMapping(path="/subscribe/{id}",produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter get(@PathVariable(value = "id") String id) throws Exception {
		return SseEmitterServer.connect(id);
    }
}
