package com.example.simpleMall.service.implementation;

import com.example.simpleMall.service.DownloadService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class DownloadServiceImp implements DownloadService {
    @Override
    public ResponseEntity<Resource> download(String filePath) throws Exception {

        File file = new File("src/main/resources/static/dist/"+filePath);


        //For the use of preventing traversal attacks
        if (file.isAbsolute())
        {
            throw new RuntimeException("Directory traversal attempt - absolute path not allowed");
        }
        String pathUsingCanonical;
        String pathUsingAbsolute;
        try
        {
            pathUsingCanonical = file.getCanonicalPath();
            pathUsingAbsolute = file.getAbsolutePath();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Directory traversal attempt?", e);
        }


        // Require the absolute path and canonicalized path match.
        // This is done to avoid directory traversal
        // attacks, e.g. "1/../2/"
        if (! pathUsingCanonical.equals(pathUsingAbsolute))
        {
            throw new RuntimeException("Directory traversal attempt?");
        }


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
