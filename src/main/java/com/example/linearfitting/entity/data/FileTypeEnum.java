package com.example.linearfitting.entity.data;

import lombok.Getter;

/**
 * @author
 */
public enum FileTypeEnum {
    // fileType  clazz
    EXCEL_XLSX("application/vnd.ms-excel", ExcelReader.class),
    EXCEL_XLS("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ExcelReader.class),
    TXT("text/plain", TxtReader.class),
    CSV("text/csv", CsvReader.class);

    @Getter
    private String fileType;
    @Getter
    private Class fileReader;

    FileTypeEnum(String fileType, Class fileReader) {
        this.fileType = fileType;
        this.fileReader = fileReader;
    }

    public static Class getClazz(String fileType) {
        for (FileTypeEnum file : FileTypeEnum.values()) {
            if (file.getFileType().equals(fileType)) {
                return file.getFileReader();
            }
        }
        return null;
    }
}
