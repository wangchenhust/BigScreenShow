package com.java.mapper;

import com.java.pojo.Index;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexxMapper {
    @Select("select name,max from indexx order by id")
    List<Index> list();
}
