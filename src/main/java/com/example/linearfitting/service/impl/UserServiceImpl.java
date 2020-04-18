package com.example.linearfitting.service.impl;

import com.example.linearfitting.entity.user.DataVO;
import com.example.linearfitting.entity.user.UserDTO;
import com.example.linearfitting.mapper.DataMapper;
import com.example.linearfitting.mapper.UserMapper;
import com.example.linearfitting.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public Boolean login(UserDTO user) {
        if (user.getPassword().equals(userMapper.findPassword(user))) {
            return true;
        }
        return false;
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
