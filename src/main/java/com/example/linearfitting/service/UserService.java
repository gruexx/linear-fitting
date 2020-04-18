package com.example.linearfitting.service;

import com.example.linearfitting.entity.user.DataVO;
import com.example.linearfitting.entity.user.UserDTO;

/**
 * @author
 */
public interface UserService {
    /**
     * 注册
     *
     * @param user
     * @return
     */
    Boolean register(UserDTO user);

    /**
     * 登录
     *
     * @param user
     * @return
     */
    Boolean login(UserDTO user);

    Object data(Integer id);

    Object save(DataVO data);

    Boolean isExist(String name);
}
