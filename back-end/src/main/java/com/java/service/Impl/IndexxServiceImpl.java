package com.java.service.Impl;

import com.java.mapper.IndexxMapper;
import com.java.pojo.Index;
import com.java.service.IndexxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexxServiceImpl implements IndexxService {
    @Autowired IndexxMapper indexxMapper;
    @Override
    public List<Index> list() {
        return indexxMapper.list();
    }
}
