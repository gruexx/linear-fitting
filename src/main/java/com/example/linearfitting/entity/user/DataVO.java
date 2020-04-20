package com.example.linearfitting.entity.user;

import lombok.Data;

import java.util.List;

/**
 * @author
 */
@Data
public class DataVO {
    private Integer id;

    private Integer userId;

    private String data;

    private String name;

    private List<List<Double>> result;
}
