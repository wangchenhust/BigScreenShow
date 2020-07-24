package com.java.service.Impl;

import com.java.mapper.StudentMapper;
import com.java.mapper.Value_Mapper;
import com.java.pojo.Series;
import com.java.pojo.Student;
import com.java.service.Value_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Value_ServiceImpl implements Value_Service {
    @Autowired
    Value_Mapper value_mapper;
    @Autowired
    StudentMapper studentMapper;
    @Override
    public List<Series> list() {
        List<Series> ss=new ArrayList<>();
        List<Student> stus=studentMapper.listStu();
        for(Student i:stus){
            List<Float> vs=value_mapper.list(i.getId());
            Series s=new Series(vs,i.getName());
            ss.add(s);
        }
        return ss;
    }
}
