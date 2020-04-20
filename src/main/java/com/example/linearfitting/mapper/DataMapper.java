package com.example.linearfitting.mapper;

import com.example.linearfitting.entity.user.DataVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 */
public interface DataMapper {
    List<DataVO> getData(@Param("userId") Integer userId);

    Integer insert(@Param("data") DataVO data);

    DataVO selectById(@Param("id") Integer id);

    Integer delete(@Param("id")Integer id);
}
