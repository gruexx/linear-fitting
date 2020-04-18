package com.example.linearfitting.mapper;

import com.example.linearfitting.entity.user.UserDTO;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 */
public interface UserMapper {
    Integer insert(@Param("user") UserDTO user);

    Integer update(@Param("user") UserDTO user);

    String findPassword(@Param("user") UserDTO user);

    Integer isExist(@Param("name") String name);
}
