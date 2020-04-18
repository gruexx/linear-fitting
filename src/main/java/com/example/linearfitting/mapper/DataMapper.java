package com.example.linearfitting.mapper;

import com.example.linearfitting.entity.user.DataVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 */
public interface DataMapper {
    List<DataVO> getData(@Param("id") Integer id);

    Integer insert(@Param("data") DataVO data);
}
