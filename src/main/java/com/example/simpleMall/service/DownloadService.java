package com.example.simpleMall.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;

public interface DownloadService {

    ResponseEntity<Resource> download(String filePath) throws FileNotFoundException;
}
