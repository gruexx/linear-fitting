package com.example.linearfitting.service.impl;

import com.example.linearfitting.entity.data.DataVO;
import com.example.linearfitting.entity.data.FileReader;
import com.example.linearfitting.entity.data.FileTypeEnum;
import com.example.linearfitting.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author
 */
@Slf4j
@Service
public class DataServiceImpl implements DataService {

    private DataVO countDataVO(List<List<Double>> data) {
        SimpleRegression regression = new SimpleRegression();
        for (List<Double> list : data) {
            if (!list.isEmpty()) {
                regression.addData(list.get(0), list.get(1));
            }
        }

        return DataVO.builder()
                .k(regression.getSlope())
                .b(regression.getIntercept())
                .fittingError(regression.getR())
                .build();
    }

    @Override
    public Object getData(MultipartFile file) {

        FileReader fileReader = FileTypeEnum.getClazz(file.getContentType());

        return fileReader.readFile(file);
    }

    @Override
    public DataVO getResult(List<List<Double>> data) {
        return countDataVO(data);
    }
}
