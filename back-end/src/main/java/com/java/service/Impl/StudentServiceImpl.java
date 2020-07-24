package com.java.service.Impl;

import com.java.mapper.StudentMapper;
import com.java.pojo.Student;
import com.java.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired StudentMapper studentMapper;
    @Override
    public List<String> list() {
        return studentMapper.list();
    }
}
