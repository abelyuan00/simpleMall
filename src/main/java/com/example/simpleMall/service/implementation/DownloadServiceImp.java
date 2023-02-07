package com.example.simpleMall.service.implementation;

import com.example.simpleMall.service.DownloadService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class DownloadServiceImp implements DownloadService {
    @Override
    public ResponseEntity<Resource> download(String filePath) throws FileNotFoundException {

        File file = new File(filePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        MediaType mediaType = MediaTypeFactory
                .getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentType(mediaType);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
