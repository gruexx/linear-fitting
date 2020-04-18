package com.example.linearfitting.entity.data;

import lombok.Getter;

/**
 * @author
 */
public enum FileTypeEnum {
    // fileType  clazz
    EXCEL_XLSX("application/vnd.ms-excel", new ExcelReader()),
    EXCEL_XLS("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new ExcelReader()),
    TXT("text/plain", new TxtReader()),
    CSV("text/csv", new CsvReader());

    @Getter
    private String fileType;
    @Getter
    private FileReader fileReader;

    FileTypeEnum(String fileType, FileReader fileReader) {
        this.fileType = fileType;
        this.fileReader = fileReader;
    }

    public static FileReader getClazz(String fileType) {
        for (FileTypeEnum file : FileTypeEnum.values()) {
            if (file.getFileType().equals(fileType)) {
                return file.getFileReader();
            }
        }
        return null;
    }
}
