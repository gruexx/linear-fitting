package com.example.linearfitting.entity.data;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author
 */
public interface FileReader {

    Object readFile(MultipartFile file);
}
