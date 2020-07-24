package com.java.controller;

import com.java.pojo.Index;
import com.java.pojo.Series;
import com.java.service.IndexxService;
import com.java.service.StudentService;
import com.java.service.Value_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据
@RestController
public class HelloController {
    @Autowired
    IndexxService indexxService;
    @Autowired
    StudentService studentService;
    @Autowired
    Value_Service value_service;

	@RequestMapping(value = "/hello")
    public String sayHello(){
        return "Hello springboot!";
    }

    @RequestMapping(value = "/listindex")
    public List<Index> list(){
	    return indexxService.list();
    }
    @RequestMapping(value = "/liststu")
    public List<String> liststu(){
        return studentService.list();
    }
    @RequestMapping(value = "/listvalue")
    public List<Series> listvalue(){
        return value_service.list();
    }
}