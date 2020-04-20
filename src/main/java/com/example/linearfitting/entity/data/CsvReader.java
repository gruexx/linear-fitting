package com.example.linearfitting.entity.data;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author
 */
@Slf4j
public class CsvReader implements FileReader {
    @Override
    public Object readFile(MultipartFile file) {
        System.out.println("csv");
        //这种读法也能用
//            DataInputStream in = new DataInputStream(file.getInputStream());
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
//            String line;
//            int i = 0;
//            while ((line = bufferedReader.readLine()) != null) {
//                if (i == 0) {
//                    i++;
//                    continue;
//                }
//                List<Double> tmp = new ArrayList<>();
//                String[] split = line.split(",");
//                for (int j = 0; j < 2; j++) {
//                    tmp.add(Double.valueOf(split[j]));
//                }
//                result.add(tmp);

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            CSVParser parser = CSVFormat.DEFAULT.parse(bufferedReader);
            List<List<Double>> values = new ArrayList<>();
            int rowIndex = 0;

            for (CSVRecord record : parser.getRecords()) {

                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }

                List<Double> value = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    if (Objects.nonNull(record.get(i))) {
                        value.add(Double.valueOf(record.get(i)));
                    }
                }
                values.add(value);
                rowIndex++;
            }
            return values;
        } catch (IOException e) {
            log.error("解析CSV内容失败" + e.getMessage(), e);
            return "csv读取出错";
        }
    }
}
