package com.example.linearfitting.service.impl;

import com.example.linearfitting.entity.data.CsvReader;
import com.example.linearfitting.entity.data.DataVO;
import com.example.linearfitting.entity.data.FileReader;
import com.example.linearfitting.entity.data.FileTypeEnum;
import com.example.linearfitting.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * @author
 */
@Slf4j
@Service
public class DataServiceImpl implements DataService {
    private final static String CSV_FILE = "csv";

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

        FileReader fileReader = null;
        try {
            String filename = file.getOriginalFilename();
            String res = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            //特别开发  旧版本excel解析以及csv给客户，由于csv获取版本莫名其妙变成excel，特别开发
            if (Objects.equals(res, CSV_FILE)) {
                fileReader = new CsvReader();
            } else {
                fileReader = (FileReader) FileTypeEnum.getClazz(file.getContentType()).newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileReader.readFile(file);
    }

    @Override
    public DataVO getResult(List<List<Double>> data) {
        return countDataVO(data);
    }
}
