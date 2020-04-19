package com.example.linearfitting.service.impl;

import com.example.linearfitting.entity.user.DataVO;
import com.example.linearfitting.entity.user.UserDTO;
import com.example.linearfitting.mapper.DataMapper;
import com.example.linearfitting.mapper.UserMapper;
import com.example.linearfitting.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private DataMapper dataMapper;


    @Override
    public Boolean register(UserDTO user) {
        if (isExist(user.getName())) {
            return false;
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userMapper.insert(user) > 0;
    }

    @Override
    public UserDTO login(UserDTO user, HttpServletResponse response, HttpServletRequest request) {
        UserDTO dto = userMapper.findPassword(user);
        if (user.getPassword().equals(dto.getPassword())) {
            Cookie cookie = new Cookie("token", dto.getId().toString());
            cookie.setComment("token");
            cookie.setPath("/demo");
            cookie.setMaxAge(30 * 60);
            response.addCookie(cookie);
            return dto;
        }
        return null;
    }

    private String getTokenFromKey(Cookie[] cookies, String key) {
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    public Object data(Integer id) {
        List<DataVO> dataVO = dataMapper.getData(id);
        for (DataVO vo : dataVO) {
            vo.setResult(transFor(vo.getData()));
        }
        return dataVO;
    }

    @Override
    public Object save(DataVO data) {
        data.setData(data.getResult().toString());
        data.setResult(null);
        return dataMapper.insert(data);
    }

    @Override
    public Boolean isExist(String name) {
        if (userMapper.isExist(name) > 0) {
            return true;
        }
        return false;
    }

    private List<List<Double>> transFor(String string) {
        String s = string.replace("[", " ")
                .replace("]", " ")
                .replace(",", " ");

        String[] split = s.split(" ");
        List<List<Double>> result = new ArrayList<>();
        List<Double> t = new ArrayList<>();
        for (String s1 : split) {
            if (!s1.isEmpty()) {
                t.add(Double.valueOf(s1));
                if (t.size() == 2) {
                    result.add(new ArrayList<>(t));
                    t.clear();
                }
            }
        }
        return result;
    }
}
