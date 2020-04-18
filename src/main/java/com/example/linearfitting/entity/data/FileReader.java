package com.example.linearfitting.entity.data;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author
 */
public interface FileReader {

    Object readFile(MultipartFile file);
}
