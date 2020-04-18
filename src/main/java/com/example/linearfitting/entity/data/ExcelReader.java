package com.example.linearfitting.entity.data;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author
 */
public class ExcelReader implements FileReader {

    @Override
    public Object readFile(MultipartFile file) {
        System.out.println("excel");

        List<List<Double>> result = new ArrayList<>();

        try {

            Workbook wb = StreamingReader.builder()
                    .rowCacheSize(100)
                    .bufferSize(4096)
                    .open(file.getInputStream());

            Sheet sheet = wb.getSheetAt(0);

            boolean flag = true;
            for (Row row : sheet) {
                if (flag) {
                    flag = false;
                    continue;
                }
                List<Double> tmp = new ArrayList<>();
                if (Objects.nonNull(row.getCell(0)) && Objects.nonNull(row.getCell(0).getStringCellValue())) {

                    Double x = Double.valueOf(row.getCell(0).getStringCellValue());
                    Double y = Double.valueOf(row.getCell(0).getStringCellValue());

                    tmp.add(x);
                    tmp.add(y);
                }
                result.add(tmp);
            }
            return result;
        } catch (Exception e) {
            return "excel 读取出错";
        }
    }
}
