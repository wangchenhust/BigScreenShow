package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Value_Mapper {
    @Select("select value_ from value_ where sid=#{sid} order by iid")
    List<Float> list(int sid);
}
