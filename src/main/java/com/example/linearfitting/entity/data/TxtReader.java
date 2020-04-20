package com.example.linearfitting.entity.data;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class TxtReader implements FileReader {
    @Override
    public Object readFile(MultipartFile file) {
        System.out.println("txt");
        List<List<Double>> result = new ArrayList<>();
        try {

            DataInputStream in = new DataInputStream(file.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            boolean i = true;
            while ((line = bufferedReader.readLine()) != null) {
                if (i) {
                    i = false;
                    continue;
                }
                List<Double> tmp = new ArrayList<>();
                String[] split = line.split(",");
                for (int j = 0; j < 2; j++) {
                    tmp.add(Double.valueOf(split[j]));
                }
                result.add(tmp);
            }
            return result;
        } catch (Exception e) {
            return "txt读取出错";
        }
    }
}
