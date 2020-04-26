package com.example.linearfitting.entity.data;

import com.google.common.collect.Lists;
import com.monitorjbl.xlsx.StreamingReader;
import io.swagger.models.auth.In;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author
 */
public class OldExcelReader implements FileReader {

    @Override
    public Object readFile(MultipartFile file) {
        System.out.println("excel");

        List<List<Double>> result = new ArrayList<>();

        try {

            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file.getInputStream());

            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);

            Map<Integer, String> map = new HashMap<>(4);
            map.put(1, "x");
            map.put(2, "y");

            for (Row row : sheet) {
                List<Double> tmp = new ArrayList<>();
                for (Cell cell : row) {
                    if (map.containsValue(getCellValue(cell))) {
                        continue;
                    }
                    if (cell == null) {
                        continue;
                    }
                    tmp.add((Double) getCellValue(cell));
                }
                if (!tmp.isEmpty()) {
                    result.add(tmp);
                }
            }

            return result;
        } catch (
                Exception e) {
            return "excel 读取出错";
        }
    }

    private Object getCellValue(Cell cell) {

        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                return cell.getStringCellValue();
            case FORMULA:
                return "x";
            case BLANK:
                return "x";
            case BOOLEAN:
                return "x";
            case ERROR:
                return "x";
            default:
                return "x";
        }
    }

}
