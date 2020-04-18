package com.example.linearfitting.service;

import com.example.linearfitting.entity.data.DataVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author
 */
public interface DataService {
    Object getData(MultipartFile file);

    DataVO getResult(List<List<Double>> data);
}
